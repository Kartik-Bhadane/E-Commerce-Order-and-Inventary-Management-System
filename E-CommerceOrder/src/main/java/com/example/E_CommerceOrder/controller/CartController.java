package com.example.E_CommerceOrder.controller;

import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.dto.AddToCartRequestdto;
import com.example.E_CommerceOrder.entity.Cart;
import com.example.E_CommerceOrder.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

   
    @PostMapping("/add")
    public Cart addToCart(@RequestBody AddToCartRequestdto dto) {
        return cartService.addToCart(dto);
    }


    @GetMapping("/{userId}")
    public Cart viewCart(@PathVariable int userId) {
        return cartService.viewCart(userId);
    }

  
    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable int userId) {
        cartService.clearCart(userId);
        return "Cart cleared successfully";
    }
}