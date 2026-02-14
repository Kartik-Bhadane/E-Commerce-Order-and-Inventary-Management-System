package com.example.E_CommerceOrder.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.dto.OrderItemRequestdto;
import com.example.E_CommerceOrder.dto.PlaceOrderRequestdto;
import com.example.E_CommerceOrder.entity.*;
import com.example.E_CommerceOrder.repository.*;
import com.example.E_CommerceOrder.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    public OrderServiceImpl(OrderRepo orderRepo,
                            UserRepo userRepo,
                            ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Order placeOrder(PlaceOrderRequestdto dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0;

        for (OrderItemRequestdto itemDto : dto.getItems()) {

            Product product = productRepo.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            Inventory inventory = product.getInventory();

            if (inventory.getQuantityAvailable() < itemDto.getQuantity()) {
                throw new RuntimeException("Out of stock");
            }

            inventory.setQuantityAvailable(
                    inventory.getQuantityAvailable() - itemDto.getQuantity()
            );

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(product.getPrice());

            totalAmount += product.getPrice() * itemDto.getQuantity();
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        return orderRepo.save(order);
    }

    @Override
    public Order cancelOrder(int orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if ("CANCELLED".equals(order.getStatus())) {
            throw new RuntimeException("Order already cancelled");
        }

        for (OrderItem item : order.getItems()) {
            Inventory inventory = item.getProduct().getInventory();
            inventory.setQuantityAvailable(
                    inventory.getQuantityAvailable() + item.getQuantity()
            );
        }

        order.setStatus("CANCELLED");
        return orderRepo.save(order);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
}