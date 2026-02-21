package com.example.E_CommerceOrder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.entity.User;
import com.example.E_CommerceOrder.repository.UserRepo;

@RestController
@RequestMapping("/api/admin/customers")
@CrossOrigin("*")
public class AdminCustomerController {

    private final UserRepo userRepo;

    public AdminCustomerController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // GET ALL CUSTOMERS (Only ROLE_CUSTOMER)
    @GetMapping
    public List<User> getAllCustomers() {
        return userRepo.findByRole("CUSTOMER");
    }
}