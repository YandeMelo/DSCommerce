package com.yandemelo.dscommercePGAdmin.dto;

import com.yandemelo.dscommercePGAdmin.entities.Category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Category")
public class CategoryDTO {
    
    private Long id;
    private String name;

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
