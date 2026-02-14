package com.example.E_CommerceOrder.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.dto.AddToCartRequestdto;
import com.example.E_CommerceOrder.entity.*;
import com.example.E_CommerceOrder.repository.*;
import com.example.E_CommerceOrder.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    public CartServiceImpl(CartRepo cartRepo,
                           UserRepo userRepo,
                           ProductRepo productRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

 
    @Override
    public Cart addToCart(AddToCartRequestdto dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepo.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setItems(new ArrayList<>());
        }

        boolean found = false;

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getProductId() == product.getProductId()) {
                item.setQuantity(item.getQuantity() + dto.getQuantity());
                found = true;
                break;
            }
        }

        if (!found) {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(dto.getQuantity());
            cart.getItems().add(item);
        }

        return cartRepo.save(cart);
    }

   
    @Override
    public Cart viewCart(int userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepo.findByUser(user);

        if (cart == null) {
            throw new RuntimeException("Cart is empty");
        }

        return cart;
    }
    @Override
    public void clearCart(int userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepo.findByUser(user);

        if (cart != null) {
            cartRepo.delete(cart);
        }
    }
}