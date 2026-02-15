package com.example.E_CommerceOrder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.E_CommerceOrder.entity.User;
import com.example.E_CommerceOrder.repository.UserRepo;

import jakarta.annotation.PostConstruct;

@Component
public class AdminInitializer {
	@Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdmin() {

        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {

            User admin = new User();
            admin.setFullName("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");

            userRepository.save(admin);

            System.out.println("Admin created!");
        }
    }
}
