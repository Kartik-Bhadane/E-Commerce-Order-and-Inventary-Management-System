package com.example.E_CommerceOrder.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.dto.OrderItemRequestdto;
import com.example.E_CommerceOrder.dto.PlaceOrderRequestdto;
import com.example.E_CommerceOrder.entity.Inventory;
import com.example.E_CommerceOrder.entity.Order;
import com.example.E_CommerceOrder.entity.OrderItem;
import com.example.E_CommerceOrder.entity.Product;
import com.example.E_CommerceOrder.entity.User;
import com.example.E_CommerceOrder.repository.InventoryRepo;
import com.example.E_CommerceOrder.repository.OrderRepo;
import com.example.E_CommerceOrder.repository.ProductRepo;
import com.example.E_CommerceOrder.repository.UserRepo;
import com.example.E_CommerceOrder.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private  OrderRepo orderRepository;
    private  UserRepo userRepository;
    private  ProductRepo productRepository;
    private  InventoryRepo inventoryRepository;

    public OrderServiceImpl(OrderRepo orderRepository,
                            UserRepo userRepository,
                            ProductRepo productRepository,
                            InventoryRepo inventoryRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Order placeOrder(PlaceOrderRequestdto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (OrderItemRequestdto itemDTO : dto.getItems()) {

            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            Inventory inventory = inventoryRepository.findByProduct(product);

            if (inventory.getQuantityAvailable() < itemDTO.getQuantity()) {
                throw new RuntimeException("Out of stock");
            }

            inventory.setQuantityAvailable(inventory.getQuantityAvailable() - itemDTO.getQuantity());
            inventoryRepository.save(inventory);

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice());

            total += product.getPrice() * itemDTO.getQuantity();
            items.add(item);
        }

        order.setTotalAmount(total);
        order.setItems(items);

        
        return orderRepository.save(order);
    }
}