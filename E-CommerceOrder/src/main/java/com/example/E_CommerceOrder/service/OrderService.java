package com.example.E_CommerceOrder.service;

import java.util.List;

import com.example.E_CommerceOrder.dto.OrderResponsedto;
import com.example.E_CommerceOrder.dto.PlaceOrderRequestdto;

public interface OrderService {

    OrderResponsedto placeOrder(PlaceOrderRequestdto dto);

    OrderResponsedto cancelOrder(int orderId);

    OrderResponsedto getOrderById(int orderId);

    List<OrderResponsedto> getAllOrders();
}