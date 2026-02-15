package com.example.E_CommerceOrder.service;

import com.example.E_CommerceOrder.dto.AddToCartRequestdto;
import com.example.E_CommerceOrder.dto.CartResponsedto;

public interface CartService {

    CartResponsedto addToCart(AddToCartRequestdto dto, String email);

    CartResponsedto viewCart(String email);

    void clearCart(String email);
}