package com.cartify.cartify.repository;

import com.cartify.cartify.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}