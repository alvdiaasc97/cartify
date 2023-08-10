package com.cartify.cartify.service;

import com.cartify.cartify.model.Cart;
import com.cartify.cartify.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    public List<Cart> getAllCarts() {
        return new ArrayList<>();
    }
}
