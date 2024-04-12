package com.yandemelo.dscommercePGAdmin.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yandemelo.dscommercePGAdmin.dto.ProductDTO;
import com.yandemelo.dscommercePGAdmin.dto.ProductMinDTO;
import com.yandemelo.dscommercePGAdmin.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Produtos", description = "Gerenciar Produtos")
@RequestMapping(value = "/products")  // Rota ou URL
public class ProductController {
    
    @Autowired
    private ProductService service;
    
    @Operation(summary = "Buscar todos os produtos")
    @ApiResponse(responseCode = "200", description = "Status 200 OK")
    @GetMapping
    public ResponseEntity<Page<ProductMinDTO>> findAll(Pageable pageable) { // Datos paginados (para não acabar carregando tudo de uma vez)
        Page<ProductMinDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status 200 OK"),
        @ApiResponse(responseCode = "404", description = "Status 404 Not Found")
    })
    @Operation(summary = "Buscar produto pelo ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Inserir um novo produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Status 201 Created"),
        @ApiResponse(responseCode = "401", description = "Status 401 Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Status 403 Forbidden")
    })
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualizar um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status 200 OK"),
        @ApiResponse(responseCode = "401", description = "Status 401 Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Status 403 Forbidden")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id,@Valid @RequestBody ProductDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Excluir um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Status 204 No Content"),
        @ApiResponse(responseCode = "401", description = "Status 401 Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Status 403 Forbidden"),
        @ApiResponse(responseCode = "404", description = "Status 404 Not Found")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
