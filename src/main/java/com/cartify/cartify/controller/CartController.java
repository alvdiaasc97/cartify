package com.cartify.cartify.controller;

import com.cartify.cartify.model.Cart;
import com.cartify.cartify.repository.CartRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CartController {
    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    @GetMapping("carts")
    public List<Cart> getCarts(){
        return cartRepository.findAll();
    }


}
