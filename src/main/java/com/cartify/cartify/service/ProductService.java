package com.cartify.cartify.service;

import com.cartify.cartify.model.Product;
import com.cartify.cartify.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product) {
         productRepository.save(product);
    }
}
