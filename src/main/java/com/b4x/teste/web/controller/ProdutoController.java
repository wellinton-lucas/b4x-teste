package com.b4x.teste.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.b4x.teste.application.service.ProdutoService;
import com.b4x.teste.application.views.ProdutoView;
import com.b4x.teste.domain.enums.CATEGORIA;
import com.b4x.teste.domain.model.Produto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("")
    public ResponseEntity<List<ProdutoView>> findAll() {
        List<ProdutoView> produtos = produtoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }
    

    @GetMapping("search")
    public ResponseEntity<List<ProdutoView>> findAllByCriteria(
            @RequestParam(required = false, name = "publicId") UUID estabelecimentoId,
            @RequestParam(required = false, name = "nome") String nome,
            @RequestParam(required = false, name = "descricao") String descricao,
            @RequestParam(required = false, name = "preco") Double preco,
            @RequestParam(required = false, name = "categoria") CATEGORIA categoria) {
        
        List<ProdutoView> produtos = produtoService.findAllByCriteria(
            estabelecimentoId, 
            nome, 
            descricao, 
            preco, 
            categoria
        );

        if(produtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoView> findById(@PathVariable UUID id) {
        ProdutoView contato = produtoService.findByPublicId(id);
        return ResponseEntity.status(HttpStatus.OK).body(contato);
    }

    @PostMapping("")
    public ResponseEntity<ProdutoView> create(@RequestBody Produto entity) {
        return ResponseEntity.ok(produtoService.create(entity));
    }

    @PatchMapping("")
    public ResponseEntity<ProdutoView> update(@RequestBody Produto entity) {
        return ResponseEntity.ok(produtoService.update(entity));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
