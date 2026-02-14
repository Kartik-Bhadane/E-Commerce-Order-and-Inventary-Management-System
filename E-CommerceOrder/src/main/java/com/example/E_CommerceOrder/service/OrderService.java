package com.example.E_CommerceOrder.service;

import java.util.List;

import com.example.E_CommerceOrder.dto.PlaceOrderRequestdto;
import com.example.E_CommerceOrder.entity.Order;

public interface OrderService {


    Order placeOrder(PlaceOrderRequestdto dto);


    Order cancelOrder(int orderId);

    // VIEW ONE ORDER
    Order getOrderById(int orderId);

 
    List<Order> getAllOrders();
}