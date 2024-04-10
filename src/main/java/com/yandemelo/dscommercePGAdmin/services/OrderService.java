package com.yandemelo.dscommercePGAdmin.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yandemelo.dscommercePGAdmin.dto.OrderDTO.OrderDTO;
import com.yandemelo.dscommercePGAdmin.dto.OrderDTO.OrderItemDTO;
import com.yandemelo.dscommercePGAdmin.entities.Order;
import com.yandemelo.dscommercePGAdmin.entities.OrderItem;
import com.yandemelo.dscommercePGAdmin.entities.OrderStatus;
import com.yandemelo.dscommercePGAdmin.entities.Product;
import com.yandemelo.dscommercePGAdmin.entities.authEntities.User;
import com.yandemelo.dscommercePGAdmin.repositories.OrderItemRepository;
import com.yandemelo.dscommercePGAdmin.repositories.OrderRepository;
import com.yandemelo.dscommercePGAdmin.repositories.ProductRepository;
import com.yandemelo.dscommercePGAdmin.services.authServices.AuthService;
import com.yandemelo.dscommercePGAdmin.services.authServices.AuthorizationService;
import com.yandemelo.dscommercePGAdmin.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AuthorizationService service;
    @Autowired
    private AuthService authService;

    //GET
    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado."));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = service.authenticated();
        order.setClient(user);

        for (OrderItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());
        return new OrderDTO(order);
    }
}
