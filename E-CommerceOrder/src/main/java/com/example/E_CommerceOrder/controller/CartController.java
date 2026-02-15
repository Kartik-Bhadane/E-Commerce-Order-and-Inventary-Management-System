package com.example.E_CommerceOrder.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.dto.AddToCartRequestdto;
import com.example.E_CommerceOrder.dto.CartResponsedto;
import com.example.E_CommerceOrder.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartResponsedto addToCart(
            @RequestBody AddToCartRequestdto dto,
            Authentication authentication
    ) {
        return cartService.addToCart(dto, authentication.getName());
    }

    @GetMapping
    public CartResponsedto viewCart(Authentication authentication) {
        return cartService.viewCart(authentication.getName());
    }

    @DeleteMapping("/clear")
    public String clearCart(Authentication authentication) {
        cartService.clearCart(authentication.getName());
        return "Cart cleared successfully";
    }
}