package com.yandemelo.dscommercePGAdmin.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yandemelo.dscommercePGAdmin.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {


     @Query("SELECT obj FROM Product obj")
     Page<Product> searchByName(Pageable pageable);

}
