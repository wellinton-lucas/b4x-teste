package com.b4x.teste.application.views;

import java.time.LocalDateTime;
import java.util.UUID;

import com.b4x.teste.domain.enums.CATEGORIA;
import com.b4x.teste.domain.model.Produto;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.fasterxml.jackson.annotation.JsonFormat;

@EntityView(Produto.class)
public interface ProdutoView {

    UUID getPublicId();
    String getNome();
    Double getPreco();
    String getDescricao();
    String getImgUrl();
    CATEGORIA getCategoria();
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime getDataCriacao();
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime getDataUltimaModificacao();
}
