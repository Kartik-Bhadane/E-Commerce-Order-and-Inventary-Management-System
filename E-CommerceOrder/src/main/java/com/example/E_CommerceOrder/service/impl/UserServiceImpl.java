package com.example.E_CommerceOrder.service.impl;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.dto.AuthResponsedto;
import com.example.E_CommerceOrder.dto.LoginRequestdto;
import com.example.E_CommerceOrder.dto.RegisterRequestdto;
import com.example.E_CommerceOrder.entity.User;
import com.example.E_CommerceOrder.repository.UserRepo;
import com.example.E_CommerceOrder.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;

    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(RegisterRequestdto request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("CUSTOMER");

        userRepository.save(user);
    }

    @Override
    public AuthResponsedto login(LoginRequestdto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

      
        String dummyToken = "DUMMY_TOKEN";

      
        return new AuthResponsedto(
                dummyToken,
                user.getRole(),
                user.getUserId()
        );
    }
}