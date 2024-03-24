package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("defaultUserService")
public class DefaultUserService implements UserService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();

    }

    @Override
    public User getUserById(int userId) {
       Optional<User> userOptional= usersRepository.findById(userId);
        return userOptional.orElse(null);
    }

    @Override
    public User saveUser(User user) {
        usersRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(int userId) {
        usersRepository.deleteById(userId);
    }
}
