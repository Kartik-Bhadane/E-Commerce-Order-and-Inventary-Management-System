package com.example.E_CommerceOrder.dto;

import java.util.List;

import com.example.E_CommerceOrder.dto.OrderItemRequestdto;

public class PlaceOrderRequestdto {

    private int userId;
    private List<OrderItemRequestdto> items;

    public PlaceOrderRequestdto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItemRequestdto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestdto> items) {
        this.items = items;
    }
}