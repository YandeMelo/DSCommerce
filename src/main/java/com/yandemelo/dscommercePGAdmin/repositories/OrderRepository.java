package com.yandemelo.dscommercePGAdmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yandemelo.dscommercePGAdmin.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
