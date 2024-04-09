package com.yandemelo.dscommercePGAdmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yandemelo.dscommercePGAdmin.entities.OrderItem;
import com.yandemelo.dscommercePGAdmin.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{
    
}
