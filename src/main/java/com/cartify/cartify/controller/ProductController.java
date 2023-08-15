package com.cartify.cartify.controller;

import com.cartify.cartify.model.Product;
import com.cartify.cartify.repository.ProductRepository;
import com.cartify.cartify.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId){
        return productRepository.findById(productId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        try {
            //Save the cart
            productRepository.save(product);

            return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error creating product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
