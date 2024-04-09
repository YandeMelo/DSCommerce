package com.yandemelo.dscommercePGAdmin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yandemelo.dscommercePGAdmin.dto.OrderDTO.OrderDTO;
import com.yandemelo.dscommercePGAdmin.services.OrderService;

@RestController
@RequestMapping(value = "/orders")  // Rota ou URL
public class OrderController {
    
    @Autowired
    private OrderService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
        OrderDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    
}
