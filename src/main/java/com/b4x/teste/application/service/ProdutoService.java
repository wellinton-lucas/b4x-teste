package com.b4x.teste.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.b4x.teste.application.views.ProdutoView;
import com.b4x.teste.domain.enums.CATEGORIA;
import com.b4x.teste.domain.model.Produto;
import com.b4x.teste.infrastructure.repository.ProdutoRepository;
import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;
    private final EntityManager em;


    public ProdutoView findByPublicId(UUID id) {
        Produto ProdutoEncontrado = produtoRepository.findByPublicId(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com o publicId " + id + " não encontrado"));
        return evm.convert(ProdutoEncontrado, ProdutoView.class);
    }

    // atualmente as buscas estão case sensitive, mas poderiam ser convertidas para case insensitive se necessário.
    // também da para adicionar paginação diretamente por aqui caso o necessário.
    public List<ProdutoView> findAllByCriteria(UUID publicId, String nome, String descricao, Double preco, CATEGORIA categoria) {
    
        CriteriaBuilder<Produto> cb = cbf.create(em, Produto.class);
        
        if (publicId != null) {
            cb.where("estabelecimentoId").eq(publicId);
        }
        
        if (nome != null && !nome.isBlank()) {
            cb.where("nome").like().value("%" + nome + "%").noEscape();
        }
        
        if (descricao != null && !descricao.isBlank()) {
            cb.where("descricao").like().value("%" + descricao + "%").noEscape();
        }
        
        if (preco != null) {
            cb.where("preco").eq(preco);
        }

        if (categoria != null) {
            cb.where("categoria").eq(categoria);
        }
        
        return evm.applySetting(
            EntityViewSetting.create(ProdutoView.class),
            cb
        ).getResultList();
    }

    public List<ProdutoView> findAll(){
        return evm.applySetting(
            EntityViewSetting.create(ProdutoView.class),
            cbf.create(em, Produto.class)
        ).getResultList();
    }

    public ProdutoView create(Produto entity) {

        LocalDateTime dataHoje = LocalDateTime.now();
        entity.setDataCriacao(dataHoje);

        Produto ProdutoCriada = produtoRepository.save(entity);
        return evm.convert(ProdutoCriada, ProdutoView.class);
    }

    public ProdutoView update(Produto entity) {
        UUID publicId = entity.getPublicId();
        if (publicId == null) {
            throw new IllegalArgumentException("Public ID is required");
        }
        
        Produto produtoEncontrado = produtoRepository.findByPublicId(publicId)
            .orElseThrow(() -> new NoSuchElementException("Produto com publicId " + publicId + " não encontrado"));
        
        if (entity.getNome() != null) {
            produtoEncontrado.setNome(entity.getNome());
        }
        if (entity.getDescricao() != null) {
            produtoEncontrado.setDescricao(entity.getDescricao());
        }
        if (entity.getPreco() != null) {
            produtoEncontrado.setPreco(entity.getPreco());
        }
        if (entity.getImgUrl() != null) {
            produtoEncontrado.setImgUrl(entity.getImgUrl());
        }
        if (entity.getCategoria() != null){
            produtoEncontrado.setCategoria(entity.getCategoria());
        }
        produtoEncontrado.setDataUltimaModificacao(entity.getDataUltimaModificacao());
            
        Produto produtoSalvo = produtoRepository.save(produtoEncontrado);
        return evm.convert(produtoSalvo, ProdutoView.class);
    }

    public void delete(UUID publicId) {

        Produto produtoEncontrado = produtoRepository.findByPublicId(publicId)
            .orElseThrow(() -> new NoSuchElementException("Produto com publicId " + publicId + " não encontrado"));
        
        produtoRepository.deleteById(produtoEncontrado.getId());
    }
    
}
