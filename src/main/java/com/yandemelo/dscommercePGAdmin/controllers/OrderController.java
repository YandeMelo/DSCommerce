package com.yandemelo.dscommercePGAdmin.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yandemelo.dscommercePGAdmin.dto.OrderDTO.OrderDTO;
import com.yandemelo.dscommercePGAdmin.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Pedidos", description = "Gerenciar Pedidos")
@RequestMapping(value = "/orders")  // Rota ou URL
public class OrderController {
    
    @Autowired
    private OrderService service;

    @Operation(summary = "Buscar pedido pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status 200 OK"),
        @ApiResponse(responseCode = "404", description = "Status 404 Not Found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
        OrderDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Adicionar um novo pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Status 201 Created"),
        @ApiResponse(responseCode = "401", description = "Status 401 Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Status 403 Forbidden")
    })
    @PostMapping
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
}
