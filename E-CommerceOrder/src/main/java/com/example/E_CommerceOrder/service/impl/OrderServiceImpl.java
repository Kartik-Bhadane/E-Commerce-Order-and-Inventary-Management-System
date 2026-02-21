package com.example.E_CommerceOrder.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.dto.AdminOrderResponsedto;
import com.example.E_CommerceOrder.dto.DashboardDTO;
import com.example.E_CommerceOrder.dto.OrderItemResponsedto;
import com.example.E_CommerceOrder.dto.OrderResponsedto;
import com.example.E_CommerceOrder.entity.*;
import com.example.E_CommerceOrder.repository.*;
import com.example.E_CommerceOrder.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final CartRepo cartRepo;

    public OrderServiceImpl(OrderRepo orderRepo,
                            UserRepo userRepo,
                            CartRepo cartRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
    }
    
    //MAP TO DTO
    private OrderResponsedto mapToDto(Order order) {

        OrderResponsedto dto = new OrderResponsedto();
        dto.setOrderId(order.getOrderId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setUserName(order.getUser().getFullName());
        dto.setUserEmail(order.getUser().getEmail());

        List<OrderItemResponsedto> itemDtos = new ArrayList<>();

        for (OrderItem item : order.getItems()) {
            itemDtos.add(new OrderItemResponsedto(
                    item.getProduct().getProductId(),
                    item.getProduct().getProductName(),
                    item.getPrice(),
                    item.getQuantity()
            ));
        }

        dto.setItems(itemDtos);
        return dto;
    }

    // 🛒 PLACE ORDER
    @Override
    public Order placeOrder(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart is empty"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0;

        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());

            totalAmount += orderItem.getPrice() * orderItem.getQuantity();
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepo.save(order);
        cartRepo.delete(cart);

        return savedOrder;
    }

    // 👤 CUSTOMER
    @Override
    public List<Order> getOrdersByCustomer(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepo.findAll().stream()
                .filter(o -> o.getUser().getUserId() == user.getUserId())
                .toList();
    }

    // 👑 ADMIN – RAW
    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    // 👑 ADMIN – DTO
    @Override
    public List<AdminOrderResponsedto> getAllOrdersForAdmin() {

        return orderRepo.findAll().stream().map(order -> {
            AdminOrderResponsedto dto = new AdminOrderResponsedto();

            dto.setOrderId(order.getOrderId());
            dto.setOrderDate(order.getOrderDate());
            dto.setStatus(order.getStatus());
            dto.setTotalAmount(order.getTotalAmount());
            dto.setCustomerEmail(order.getUser().getEmail());

            return dto;
        }).toList();
    }
    
    
    @Override
    public Order cancelOrderByAdmin(int orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if ("CANCELLED".equals(order.getStatus())) {
            throw new RuntimeException("Order already cancelled");
        }

        order.setStatus("CANCELLED");
        return orderRepo.save(order); // ✅ SAVE TO DB
    }

	@Override
	public DashboardDTO getDashboardStats() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 @Override
	    public Long totalOrders() {
	    	return orderRepo.count();
	    }
	    
	    @Override
	    public Double tatalRevenue() {
	    	double total=0;
	    	for(Order order:orderRepo.findAll()) {
	    		total += order.getTotalAmount();
	    	}
	    	return total;
	    }
	    
	    //GET ALL ORDERS ACCORDING TO DATE
	    @Override
	    public List<OrderResponsedto> getAllOrder() {
	    	
	    	List<Order> orders = orderRepo.findAll();
	    	orders.sort((a, b) -> b.getOrderDate().compareTo(a.getOrderDate()));
	    	
	    	

	        List<OrderResponsedto> response = new ArrayList<>();
	        
	        for (Order order : orderRepo.findAll()) {
	            response.add(mapToDto(order));
	        }
	        return response;
	    }
}