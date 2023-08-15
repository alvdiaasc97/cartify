package com.cartify.cartify;

import com.cartify.cartify.model.Cart;
import com.cartify.cartify.model.Product;
import com.cartify.cartify.repository.CartRepository;
import com.cartify.cartify.service.CartService;
import com.cartify.cartify.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final CartService cartService;
    private final ProductService productService;
    private final CartRepository cartRepository;

    @Autowired
    public DataInitializer(CartService cartService, ProductService productService,
                           CartRepository cartRepository) {
        this.cartService = cartService;
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run(String... args) {
        // Create and save example products
        Product product1 = new Product();
        product1.setAmount(10.0);
        product1.setDescription("Product 1");
        productService.createProduct(product1);

        Product product2 = new Product();
        product2.setAmount(20.0);
        product2.setDescription("Product 2");
        productService.createProduct(product2);

        Product product3 = new Product();
        product3.setAmount(5.6);
        product3.setDescription("Product 3");
        productService.createProduct(product3);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        // Create and save example carts with products
        Cart cart1 = new Cart();
        cart1.setActivityTime(LocalDateTime.now().minusMinutes(10));
        cartService.createCart(cart1);
        cart1.setProducts(products);
        cartRepository.save(cart1);

        Cart cart2= new Cart();
        cart2.setActivityTime(LocalDateTime.now());
        cartService.createCart(cart2);
        cartService.addProduct(cart2.getId(), product3.getId());
        cartRepository.save(cart2);


    }
}
