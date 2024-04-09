package com.yandemelo.dscommercePGAdmin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yandemelo.dscommercePGAdmin.dto.OrderDTO.OrderDTO;
import com.yandemelo.dscommercePGAdmin.entities.Order;
import com.yandemelo.dscommercePGAdmin.repositories.OrderRepository;
import com.yandemelo.dscommercePGAdmin.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository repository;

    //GET
    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado."));
        return new OrderDTO(order);
    }
}
