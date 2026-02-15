package com.example.E_CommerceOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.dto.AuthResponsedto;
import com.example.E_CommerceOrder.dto.LoginRequestdto;
import com.example.E_CommerceOrder.dto.RegisterRequestdto;
import com.example.E_CommerceOrder.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestdto request) {
        authService.register(request);
        return "Customer registered successfully";
    }

    @PostMapping("/login")
    public AuthResponsedto login(@RequestBody LoginRequestdto request) {
        return authService.login(request);
    }

    @GetMapping("/show")
    public String show() {
        return "Hello";
    }
}