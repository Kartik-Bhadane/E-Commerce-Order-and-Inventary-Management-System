package com.example.E_CommerceOrder.dto;

import java.util.List;

public class CartResponsedto {

    private int cartId;
    private List<CartItemResponsedto> items;
    private double totalAmount;

    public CartResponsedto(int cartId, List<CartItemResponsedto> items, double totalAmount) {
        this.cartId = cartId;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public int getCartId() { return cartId; }
    public List<CartItemResponsedto> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
}