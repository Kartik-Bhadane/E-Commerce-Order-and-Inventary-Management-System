package com.example.E_CommerceOrder.service;

import com.example.E_CommerceOrder.dto.PlaceOrderRequestdto;
import com.example.E_CommerceOrder.entity.*;


public interface OrderService {

    Order placeOrder(PlaceOrderRequestdto dto);
}