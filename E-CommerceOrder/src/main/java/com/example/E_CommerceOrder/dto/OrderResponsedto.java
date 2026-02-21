package com.example.E_CommerceOrder.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponsedto {

    private int orderId;
    private LocalDateTime orderDate;
    private String status;
    private double totalAmount;
    private List<OrderItemResponsedto> items;
    private String userName;
    private String userEmail;

    public OrderResponsedto() {}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItemResponsedto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponsedto> items) {
        this.items = items;
    }
    
    public String getUserName() {
    	return userName;
    }
    
    public void setUserName(String userName) {
    	this.userName=userName;
    }
    
    public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}