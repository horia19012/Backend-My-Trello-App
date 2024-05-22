package com.example.TaskManagement.controller;

import com.example.TaskManagement.DTO.LoginRequest;
import com.example.TaskManagement.service.impl.AuthenticationService;
import com.example.TaskManagement.service.impl.DefaultUserService;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {


    private final AuthenticationService authenticationService;


    private final DefaultUserService userService;


    @Autowired
    public LoginController(AuthenticationManager authenticationManager, DefaultUserService userService, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authenticationService.authenticate(loginRequest);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


}
