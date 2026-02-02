package com.b4x.teste.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.b4x.teste.application.utils.UuidUtils;
import com.b4x.teste.domain.enums.ROLES;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", unique = true)
    private UUID publicId;

    @Column(length = 64)
    private String login;

    @Column(length = 64)
    private String senha;

    @Enumerated(EnumType.STRING)
    private ROLES roles; 

    @PrePersist
    protected void onCreate() {
        if (this.publicId == null) {
            this.publicId = UuidUtils.randomV7();
        }
    }
}
