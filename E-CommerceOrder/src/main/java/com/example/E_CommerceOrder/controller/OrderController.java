package com.example.E_CommerceOrder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.dto.OrderResponsedto;
import com.example.E_CommerceOrder.dto.PlaceOrderRequestdto;
import com.example.E_CommerceOrder.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public OrderResponsedto placeOrder(@RequestBody PlaceOrderRequestdto dto) {
        return orderService.placeOrder(dto);
    }

    @PutMapping("/cancel/{orderId}")
    public OrderResponsedto cancelOrder(@PathVariable int orderId) {
        return orderService.cancelOrder(orderId);
    }

    @GetMapping("/{orderId}")
    public OrderResponsedto getOrder(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping
    public List<OrderResponsedto> getAllOrders() {
        return orderService.getAllOrders();
    }
}