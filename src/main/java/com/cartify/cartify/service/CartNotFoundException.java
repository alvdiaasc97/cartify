package com.cartify.cartify.service;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String s) {
        super(s);
    }
}
