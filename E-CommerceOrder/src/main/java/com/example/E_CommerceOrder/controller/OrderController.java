package com.example.E_CommerceOrder.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.entity.Order;
import com.example.E_CommerceOrder.service.OrderService;

@RestController
@RequestMapping("/api/customer/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 🛒 PLACE ORDER (FROM CART)
    @PostMapping("/place")
    public Order placeOrder(Authentication authentication) {
        return orderService.placeOrder(authentication.getName());
    }

    // 👤 CUSTOMER – VIEW OWN ORDERS
    @GetMapping
    public List<Order> myOrders(Authentication authentication) {
        return orderService.getOrdersByCustomer(authentication.getName());
    }
}