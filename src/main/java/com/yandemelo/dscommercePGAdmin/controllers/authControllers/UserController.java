package com.yandemelo.dscommercePGAdmin.controllers.authControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yandemelo.dscommercePGAdmin.dto.UserDTO;
import com.yandemelo.dscommercePGAdmin.services.authServices.AuthorizationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private AuthorizationService service;

    @Operation(summary = "Buscar dados do usuário logado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
    })
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe() {
    	UserDTO dto = service.getMe();
        return ResponseEntity.ok(dto);
    }
}
