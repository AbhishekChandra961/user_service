package com.stockTrade.user_service.service;

import com.stockTrade.user_service.dto.LoginRequest;
import com.stockTrade.user_service.entity.User;
import com.stockTrade.user_service.exception.UserNotFoundException;
import com.stockTrade.user_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "JWT_TOKEN"; // Mock JWT token
        } else {
            throw new RuntimeException("Invalid credentials!");
        }
    }
}
