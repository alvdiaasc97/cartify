package com.cartify.cartify.service;

import com.cartify.cartify.model.Cart;
import com.cartify.cartify.model.Product;
import com.cartify.cartify.repository.CartRepository;
import com.cartify.cartify.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void addProduct(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            Product product = getProductById(productId);
            if (product != null) {
                if (cart.getProducts() == null) {
                    cart.setProducts(new ArrayList<>()); // Crear la lista si es nula
                }
                cart.getProducts().add(product);
                cartRepository.save(cart);
            } else {
                throw new ProductNotFoundException("The product with ID " + productId + " could not be found.");
            }
        } else {
            throw new CartNotFoundException("The cart with ID " + cartId + " could not be found.");
        }
    }

    @Transactional
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }


    public void createCart(Cart cart) {
         cartRepository.save(cart);
    }
}
