package com.example.E_CommerceOrder.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.config.JwtUtil;
import com.example.E_CommerceOrder.dto.AuthResponsedto;
import com.example.E_CommerceOrder.dto.LoginRequestdto;
import com.example.E_CommerceOrder.dto.RegisterRequestdto;
import com.example.E_CommerceOrder.entity.User;
import com.example.E_CommerceOrder.exception.AuthException;
import com.example.E_CommerceOrder.repository.UserRepo;
import com.example.E_CommerceOrder.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepo repo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(UserRepo repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    // ================================
    // REGISTER
    // ================================
    @Override
    public void register(RegisterRequestdto request) throws AuthException {

        if (repo.findByEmail(request.email).isPresent()) {
            throw new AuthException("Email already registered");
        }

        User user = new User();
        user.setFullName(request.fullName);
        user.setEmail(request.email);
        user.setPassword(encoder.encode(request.password));
        user.setRole("CUSTOMER");

        repo.save(user);
    }

    // ================================
    // LOGIN
    // ================================
    @Override
    public AuthResponsedto login(LoginRequestdto request) throws AuthException {

        User user = repo.findByEmail(request.email)
                .orElseThrow(() -> new AuthException("Invalid credentials"));

        if (!encoder.matches(request.password, user.getPassword())) {
            throw new AuthException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponsedto(
                token,
                user.getRole(),
                user.getUserId()
        );
    }
}