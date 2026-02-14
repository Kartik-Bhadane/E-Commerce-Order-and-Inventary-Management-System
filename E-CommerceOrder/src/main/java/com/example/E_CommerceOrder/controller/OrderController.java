package com.example.E_CommerceOrder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.dto.PlaceOrderRequestdto;
import com.example.E_CommerceOrder.entity.Order;
import com.example.E_CommerceOrder.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public Order placeOrder(@RequestBody PlaceOrderRequestdto dto) {
        return orderService.placeOrder(dto);
    }

    @PutMapping("/cancel/{orderId}")
    public Order cancelOrder(@PathVariable int orderId) {
        return orderService.cancelOrder(orderId);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}