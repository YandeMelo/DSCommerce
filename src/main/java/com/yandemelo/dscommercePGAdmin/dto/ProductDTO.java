package com.yandemelo.dscommercePGAdmin.dto;

import java.util.ArrayList;
import java.util.List;

import com.yandemelo.dscommercePGAdmin.entities.Category;
import com.yandemelo.dscommercePGAdmin.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
    
    private Long id;
    
    @Size(min = 3, max = 80, message = "Name must have between 3 and 80 characters")
    @NotBlank(message = "Required")
    private String name;

    @Size(min = 10, message = "Must have at least 10 characters")
    @NotBlank(message = "Required")
    private String description;

    @Positive(message = "Price must be positive")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "Must have at least 1 category")
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    // Facilitar a passada das informações para o DTO (para não precisar passar cada parâmetro)
    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        for(Category cat : entity.getCategories()){
            categories.add(new CategoryDTO(cat));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

}
