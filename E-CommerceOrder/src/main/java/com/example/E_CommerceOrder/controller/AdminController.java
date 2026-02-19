package com.example.E_CommerceOrder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.entity.Order;
import com.example.E_CommerceOrder.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminController {

    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    //  – VIEW ALL ORDERS
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    //  – CANCEL ORDER
    @PutMapping("/{orderId}/cancel")
    public Order cancelOrder(@PathVariable int orderId) {
        return orderService.cancelOrderByAdmin(orderId);
    }
}