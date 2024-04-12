package com.yandemelo.dscommercePGAdmin.dto.OrderDTO;

import com.yandemelo.dscommercePGAdmin.entities.authEntities.User;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "User Min")
public class UserMinDTO {
    
    private Long id;
    private String name;

    public UserMinDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserMinDTO(User entity) {
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
