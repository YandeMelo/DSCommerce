package com.yandemelo.dscommercePGAdmin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yandemelo.dscommercePGAdmin.dto.CategoryDTO;
import com.yandemelo.dscommercePGAdmin.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Categorias", description = "Verificar categorias")
@RequestMapping(value = "/categories")  // Rota ou URL
public class CategoryController {
    
    @Autowired
    private CategoryService service;
    
    @Operation(summary = "Buscar todas as categorias")
    @ApiResponse(responseCode = "200", description = "Status 200 OK")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() { 
        List<CategoryDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

 

}
