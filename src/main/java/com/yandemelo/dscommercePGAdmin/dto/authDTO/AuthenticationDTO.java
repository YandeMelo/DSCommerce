package com.yandemelo.dscommercePGAdmin.dto.authDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Login")
public record AuthenticationDTO(String email, String password) {
    
}
