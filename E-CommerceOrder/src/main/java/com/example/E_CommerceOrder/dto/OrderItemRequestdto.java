package com.example.E_CommerceOrder.dto;

public class OrderItemRequestdto {

    private int productId;
    private Integer quantity;

    public OrderItemRequestdto() {
    }

    public int getProductId() {
        return productId;
        
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}