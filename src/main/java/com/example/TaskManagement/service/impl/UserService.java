package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();


    User getUserById(int userId);

    User saveUser(User user);
    void deleteUser(int userId);



}