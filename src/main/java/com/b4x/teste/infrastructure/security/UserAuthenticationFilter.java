package com.b4x.teste.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.b4x.teste.domain.model.Usuario;
import com.b4x.teste.infrastructure.repository.UsuarioRepository;

import java.io.IOException;
import java.util.Arrays;

import static com.b4x.teste.infrastructure.security.SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED;
import static com.b4x.teste.infrastructure.security.SecurityConfiguration.PUBLIC_GET_ENDPOINTS;
@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UsuarioRepository userRepository;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    public UserAuthenticationFilter(JwtTokenService jwtTokenService, UsuarioRepository usuariosRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = usuariosRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        logger.debug("Processando requisição para: " + requestURI);

        if (isPublicEndpoint(request)) {
            logger.debug("Endpoint público - liberando acesso");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = recoveryToken(request);
            if (token == null || token.isEmpty()) {
                throw new RuntimeException("Token de autenticação não fornecido");
            }

            logger.debug("Validando token JWT");
            String subject = jwtTokenService.getSubjectFromToken(token);

            Usuario user = userRepository.findByLogin(subject)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o token fornecido"));

            UserDetailsImpl userDetails = new UserDetailsImpl(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("Autenticação configurada para usuário: " + user.getLogin());

            filterChain.doFilter(request, response);

        } catch (RuntimeException e) {
            logger.error("Falha na autenticação: " + e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Erro de autenticação: " + e.getMessage());
        }
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        boolean isNonRequiredAuthEndpoint = Arrays.stream(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED)
                .anyMatch(pattern -> antPathMatcher.match(pattern, requestURI));

        if (isNonRequiredAuthEndpoint) {
            return true;
        }

        if ("GET".equalsIgnoreCase(method)) {
            return Arrays.stream(PUBLIC_GET_ENDPOINTS)
                    .anyMatch(pattern -> antPathMatcher.match(pattern, requestURI));
        }

        if ("POST".equalsIgnoreCase(method)) {
            return requestURI.startsWith("/pedido") || requestURI.startsWith("/cliente") || requestURI.startsWith("/usuario");
        }

        return false;
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}