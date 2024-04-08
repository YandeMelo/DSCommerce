package com.yandemelo.dscommercePGAdmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.yandemelo.dscommercePGAdmin.entities.User;

public interface UserRepository extends JpaRepository <User, Long>{
        
    UserDetails findByEmail(String email);
        
}
