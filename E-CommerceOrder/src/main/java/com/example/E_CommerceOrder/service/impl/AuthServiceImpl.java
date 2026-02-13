package com.example.E_CommerceOrder.service.impl;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.dto.AuthResponsedto;
import com.example.E_CommerceOrder.dto.LoginRequestdto;
import com.example.E_CommerceOrder.dto.RegisterRequestdto;
import com.example.E_CommerceOrder.entity.User;
import com.example.E_CommerceOrder.repository.UserRepo;
import com.example.E_CommerceOrder.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private  UserRepo userRepository;

    public AuthServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(RegisterRequestdto request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("USER");

        userRepository.save(user);
    }

    @Override
    public AuthResponsedto login(LoginRequestdto request) {

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new AuthResponsedto("dummy-token", user.getRole());
    }
}