package com.b4x.teste.domain.enums;

import lombok.Getter;

@Getter
public enum ROLES {
    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE");

    private final int valorRoles;
    private final String authorityName;

    ROLES(int valor, String authorityName) {
        this.valorRoles = valor;
        this.authorityName = authorityName;
    }
}
