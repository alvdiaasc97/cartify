package com.cartify.cartify.controller;

import com.cartify.cartify.model.Cart;
import com.cartify.cartify.model.Product;
import com.cartify.cartify.repository.CartRepository;
import com.cartify.cartify.service.CartNotFoundException;
import com.cartify.cartify.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartRepository cartRepository;
    private final CartService cartService;

    public CartController(CartRepository cartRepository, CartService cartService) {
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }


    @GetMapping()
    public List<Cart> getCarts()  {
        try{
            return cartRepository.findAll();
        } catch (Exception e){
            throw new CartNotFoundException("Exception getting carts"+e);
        }
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId){
        try{
            return cartRepository.findById(cartId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<String> createCart(@RequestBody Cart cart) {
        try {
            //Set activity time
            cart.setActivityTime(LocalDateTime.now());
            //Save the cart
            cartRepository.save(cart);

            return new ResponseEntity<>("Cart created successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error creating cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<String> addProductToCart(
            @PathVariable Long cartId,
            @RequestBody Product product) {
        try{
            cartService.addProduct(cartId, product.getId());
            return ResponseEntity.ok("Products added to cart successfully");
        }catch (Exception e){
            return new ResponseEntity<>("Error adding products to the cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{cartId}/delete")
    public ResponseEntity<String> removeCart(
            @PathVariable Long cartId) {
        try{
            cartRepository.deleteById(cartId);
            return ResponseEntity.ok("Cart deleted successfully");
        }catch (Exception e){
            return new ResponseEntity<>("Error deleting the cart", HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }





}
