package com.yandemelo.dscommercePGAdmin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yandemelo.dscommercePGAdmin.dto.CategoryDTO;
import com.yandemelo.dscommercePGAdmin.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")  // Rota ou URL
public class CategoryController {
    
    @Autowired
    private CategoryService service;
    
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() { 
        List<CategoryDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

 

}
