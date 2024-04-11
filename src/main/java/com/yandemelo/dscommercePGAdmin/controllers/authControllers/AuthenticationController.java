package com.yandemelo.dscommercePGAdmin.controllers.authControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yandemelo.dscommercePGAdmin.config.TokenService;
import com.yandemelo.dscommercePGAdmin.dto.authDTO.AuthenticationDTO;
import com.yandemelo.dscommercePGAdmin.dto.authDTO.LoginResponseDTO;
import com.yandemelo.dscommercePGAdmin.dto.authDTO.RegisterDTO;
import com.yandemelo.dscommercePGAdmin.entities.authEntities.User;
import com.yandemelo.dscommercePGAdmin.repositories.authRepositories.UserRepository;
import com.yandemelo.dscommercePGAdmin.services.exceptions.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Autenticação de usuários")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Fazer login de usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status 200 OK"),
        @ApiResponse(responseCode = "403", description = "Status 403 Forbidden"),
        @ApiResponse(responseCode = "404", description = "Status 404 Not Found")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }
    
    @Operation(summary = "Registrar usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Status 201 Created"),
        @ApiResponse(responseCode = "403", description = "Status 403 Forbidden"),
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        if (this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), data.phone(), data.birthDate(), encryptedPassword, data.role());

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
