package com.online.store.controller;

import com.online.store.dto.CartDTO;
import com.online.store.exception.ApiException;
import com.online.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping()
    public CartDTO saveCart(@RequestBody CartDTO cart) {
        try {
            return cartService.saveCart(cart);
        } catch (Exception ex) {
            throw new ApiException("Could not save/update shopping cart!");
        }
    }

    @GetMapping
    public CartDTO getCartByUserId(@RequestParam Long userId) {
        try {
            return cartService.getCartByUserId(userId);
        } catch (Exception ex) {
            throw new ApiException("Could not get cart for user with ID = " + userId.toString());
        }
    }
}
