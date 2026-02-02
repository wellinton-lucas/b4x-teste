package com.b4x.teste.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b4x.teste.application.dto.LoginUserDto;
import com.b4x.teste.application.dto.RecoveryJwtTokenDto;
import com.b4x.teste.application.service.UsuarioService;
import com.b4x.teste.infrastructure.security.SecurityConfiguration;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/login")
public class LoginController {

    private final UsuarioService userService;
    private final SecurityConfiguration securityConfiguration;

    @Autowired
    public LoginController(UsuarioService userService, SecurityConfiguration securityConfiguration) {
        this.userService = userService;
        this.securityConfiguration = securityConfiguration;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
            try {
                RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
                return ResponseEntity.ok(token);
            } catch (BadCredentialsException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
            }
        }



    @GetMapping("test")
    public ResponseEntity<String> getAuthenticationTest() {
        String senha = "1234";
        String senhaCriptografada = securityConfiguration.passwordEncoder().encode(senha);
        System.out.println(senhaCriptografada);
        return ResponseEntity.ok("true");
    }

    @GetMapping("hash")
    public ResponseEntity<String> getMethodName() {
        String senha = "1234";
        String senhaCriptografada = securityConfiguration.passwordEncoder().encode(senha);
        return ResponseEntity.ok(senhaCriptografada);
    }
    
}
