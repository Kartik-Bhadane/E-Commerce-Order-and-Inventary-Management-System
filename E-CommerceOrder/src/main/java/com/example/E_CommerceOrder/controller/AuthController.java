package com.example.E_CommerceOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_CommerceOrder.dto.AuthResponsedto;
import com.example.E_CommerceOrder.dto.LoginRequestdto;
import com.example.E_CommerceOrder.dto.RegisterRequestdto;
import com.example.E_CommerceOrder.service.impl.AuthServiceImpl;
//import com.example.demo.dto.AuthResponse;
//import com.example.demo.dto.LoginRequest;
//import com.example.demo.dto.RegisterRequest;
//import com.example.demo.service.AuthService;

import jakarta.security.auth.message.AuthException;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin
public class AuthController {

	
    private final AuthServiceImpl service;

    @Autowired
    public AuthController(AuthServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestdto request) throws AuthException {
        service.register(request);
        return "Customer registered successfully";
    }

    @PostMapping("/login")
    public AuthResponsedto login(@RequestBody LoginRequestdto request) throws AuthException {
        return service.login(request);
    }
    
    @GetMapping("/show")
    public String show() {
    	return "Hellow ";
    }
}
