package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.repository.UsersRepository;
import com.example.TaskManagement.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultCustomUserDetailsService implements CustomUserDetailsService {

    @Autowired
    private final UsersRepository usersRepository;

    public DefaultCustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        User foundUser=user.get();
        return org.springframework.security.core.userdetails.User.builder()
                .username(foundUser.getUsername())
                .password(foundUser.getPassword())
                .roles(foundUser.getRole().toString())
                .build();
    }
}
