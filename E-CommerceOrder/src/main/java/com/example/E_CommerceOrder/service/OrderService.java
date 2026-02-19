package com.example.E_CommerceOrder.service;

import java.util.List;

import com.example.E_CommerceOrder.dto.AdminOrderResponsedto;
import com.example.E_CommerceOrder.entity.Order;

public interface OrderService {

    // Customer places order from cart
    Order placeOrder(String email);

    //  Customer views own orders
    List<Order> getOrdersByCustomer(String email);

    //  Admin views all orders
    List<Order> getAllOrders();
    
    List<AdminOrderResponsedto> getAllOrdersForAdmin();
    
    Order cancelOrderByAdmin(int orderId);
}