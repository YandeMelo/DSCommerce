package com.yandemelo.dscommercePGAdmin.dto;

import java.time.LocalDate;

import com.yandemelo.dscommercePGAdmin.entities.UserRole;

public record RegisterDTO(String name, String email, String phone, LocalDate birthDate, String password, UserRole role) {
    
}
