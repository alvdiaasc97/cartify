package com.cartify.cartify.controller;

import com.cartify.cartify.model.Cart;
import com.cartify.cartify.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("carts")
    public List<Cart> getCarts(){
        return cartService.getAllCarts();
    }

}
