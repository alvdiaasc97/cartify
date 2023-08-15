package com.cartify.cartify.controller;

import com.cartify.cartify.model.Cart;
import com.cartify.cartify.repository.CartRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableScheduling
public class CleanUpScheduler {

    private final CartRepository cartRepository;

    public CleanUpScheduler(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Scheduled(fixedDelay = 60000) //Execute every 10 minutes
    public void cleanupExpiredCarts() {
        // Calculate 10 minutes from local time
        LocalDateTime limitTime = LocalDateTime.now().minusMinutes(10);
        List<Cart> carts = cartRepository.findByActivityTimeBefore(limitTime);
        cartRepository.deleteAll(carts);
    }
}

