package com.example.demo.controller;

import com.example.demo.service.impl.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        super();
        this.userService=userService;
    }



}
