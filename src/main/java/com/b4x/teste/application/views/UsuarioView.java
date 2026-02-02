package com.b4x.teste.application.views;

import java.time.LocalDateTime;
import java.util.UUID;

import com.b4x.teste.domain.enums.ROLES;
import com.b4x.teste.domain.model.Usuario;
import com.blazebit.persistence.view.EntityView;

import jakarta.persistence.Id;

@EntityView(Usuario.class)
public interface UsuarioView {

    UUID getPublicId();
    String getLogin();
    String getSenha();
    ROLES getRoles();

}
