package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.DTO.LoginRequest;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.repository.UsersRepository;

import com.example.TaskManagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(UsersRepository usersRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(LoginRequest loginRequest) {
        User user = usersRepository.findByUsername(loginRequest.getUsername())
                .orElse(null);
        if (user == null) {
            return null;
        }
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        }
        return null;
    }
}