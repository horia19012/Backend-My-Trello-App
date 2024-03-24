package com.example.demo.service.impl;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User putUser(User user);
    void deleteUser(Long userId);



}
