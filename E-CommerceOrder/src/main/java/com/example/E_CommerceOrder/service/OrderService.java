package com.example.E_CommerceOrder.service;

import java.util.List;

import com.example.E_CommerceOrder.dto.AdminOrderResponsedto;
import com.example.E_CommerceOrder.dto.DashboardDTO;
import com.example.E_CommerceOrder.dto.OrderResponsedto;
import com.example.E_CommerceOrder.entity.Order;

public interface OrderService {

    // Dashboard
    DashboardDTO getDashboardStats();

    // Customer
    Order placeOrder(String email);
    List<Order> getOrdersByCustomer(String email);

    // Admin
    List<Order> getAllOrders();
    List<AdminOrderResponsedto> getAllOrdersForAdmin();
    Order cancelOrderByAdmin(int orderId);
    
    Long totalOrders(); // new method add
    Double tatalRevenue(); // new method add
    List<OrderResponsedto> getAllOrder();
}