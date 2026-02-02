package com.b4x.teste.application.dto;

import com.b4x.teste.application.views.UsuarioView;

public record RecoveryJwtTokenDto(

        String token,
        UsuarioView usuario
) {
}
