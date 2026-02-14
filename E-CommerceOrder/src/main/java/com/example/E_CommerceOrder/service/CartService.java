package com.example.E_CommerceOrder.service;

import com.example.E_CommerceOrder.dto.AddToCartRequestdto;
import com.example.E_CommerceOrder.entity.Cart;

public interface CartService {

    Cart addToCart(AddToCartRequestdto dto);

    Cart viewCart(int userId);

    void clearCart(int userId);
}