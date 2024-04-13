package com.yandemelo.dscommercePGAdmin.dto.authDTO;

import java.time.LocalDate;

import com.yandemelo.dscommercePGAdmin.entities.authEntities.UserRole;

public record RegisterDTO(String name, String email, String phone, LocalDate birthDate, String password, UserRole role) {
    
}
