package com.b4x.teste.application.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.b4x.teste.application.dto.LoginUserDto;
import com.b4x.teste.application.dto.RecoveryJwtTokenDto;
import com.b4x.teste.application.views.UsuarioView;
import com.b4x.teste.domain.model.Usuario;
import com.b4x.teste.infrastructure.repository.UsuarioRepository;
import com.b4x.teste.infrastructure.security.JwtTokenService;
import com.b4x.teste.infrastructure.security.UserDetailsImpl;
import com.blazebit.persistence.view.EntityViewManager;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UsuarioRepository usuarioRepository;
    private final EntityViewManager evm;

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

            Authentication authentication = this.authenticationManager.authenticate(authToken);

            Usuario usuario = this.usuarioRepository.findByLogin(loginUserDto.email())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            UsuarioView usuarioView = evm.convert(usuario, UsuarioView.class);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return new RecoveryJwtTokenDto(this.jwtTokenService.generateToken(userDetails), usuarioView);

        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Credenciais inválidas", ex);
        }
    }

}
