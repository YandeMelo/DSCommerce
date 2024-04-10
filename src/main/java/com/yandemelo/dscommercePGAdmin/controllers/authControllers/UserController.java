package com.yandemelo.dscommercePGAdmin.controllers.authControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yandemelo.dscommercePGAdmin.dto.UserDTO;
import com.yandemelo.dscommercePGAdmin.services.authServices.AuthorizationService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private AuthorizationService service;

    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe() {
    	UserDTO dto = service.getMe();
        return ResponseEntity.ok(dto);
    }
}
