package com.example.E_CommerceOrder.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_CommerceOrder.dto.CustomerProfileResponsedto;
import com.example.E_CommerceOrder.entity.User;
import com.example.E_CommerceOrder.repository.UserRepo;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final UserRepo userRepo;

    public CustomerController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/profile")
    public CustomerProfileResponsedto getProfile(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new CustomerProfileResponsedto(
                user.getUserId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole()
        );
    }
}