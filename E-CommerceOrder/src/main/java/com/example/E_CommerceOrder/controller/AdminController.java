package com.example.E_CommerceOrder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.dto.OrderResponsedto;
import com.example.E_CommerceOrder.entity.Order;
import com.example.E_CommerceOrder.service.OrderService;
import com.example.E_CommerceOrder.service.ProductService;
import com.example.E_CommerceOrder.service.UserService;


@RestController
@RequestMapping("/api/admin/orders")
public class AdminController {

    private final OrderService orderService;
    private final UserService userService;
	private final ProductService productService;

	public AdminController(UserService userService, ProductService productService,
			OrderService orderService) {
		this.userService = userService;
		this.productService = productService;
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
    
    @GetMapping("/totalcustomer")
	public Long totalCutomer() {
		return userService.totalCustmer();
	}
	
	@GetMapping("/totalorders")
	public Long totalOrder() {
		return orderService.totalOrders();
	}
	
	@GetMapping("/totalproducts")
	public Long totalProduct() {
		return productService.totalProducts();
	}
	
	@GetMapping("/totalrevenue")
	public Double totalRevenue() {
		return orderService.tatalRevenue();
	}
	
	// view latest order 
	@GetMapping("getorder")
    public List<OrderResponsedto> getAllOrder() {
        return orderService.getAllOrder();
    }
	
    
    
}