package com.cartify.cartify.controller;

import com.cartify.cartify.repository.ProductRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
