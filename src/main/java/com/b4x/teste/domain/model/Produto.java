package com.b4x.teste.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.b4x.teste.application.utils.GeneratedUuidV7;
import com.b4x.teste.application.utils.UuidUtils;
import com.b4x.teste.domain.enums.CATEGORIA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", unique = true)
    private UUID publicId;

    @Column(length = 64)
    private String nome;

    @Column(length = 124)
    private String descricao;

    private Double preco;

    // normalmente a gente usa um storage de imagens para armazenar as imagens como o S3 da amazon pq sai mais barato, ai na hora da criação
    // da pra fazer uma lógica simples de criar um link para a imagem na s3 e preencher esse campo automaticamente.
    @Column(length = 1024)
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    private CATEGORIA categoria;

    @CreationTimestamp
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @CreationTimestamp
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "data_ultima_modificacao")
    private LocalDateTime dataUltimaModificacao;

    @PrePersist
    protected void onCreate() {
        if (this.publicId == null) {
            this.publicId = UuidUtils.randomV7();
        }
    }
}
