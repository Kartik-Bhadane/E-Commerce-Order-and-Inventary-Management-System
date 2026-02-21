package com.example.E_CommerceOrder.dto;

public class RecentOrderdto {

    private int orderId;
    private String customerName;
    private double totalAmount;
    private String status;

    public RecentOrderdto(int orderId, String customerName,
                          double totalAmount, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }
}