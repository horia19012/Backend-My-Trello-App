package com.example.TaskManagement.mapper;

import com.example.TaskManagement.DTO.UserDTO;
import com.example.TaskManagement.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }

    public static User toEntity(UserDTO userDTO) {
        return new User(
                userDTO.getUsername(),
                userDTO.getFullName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getRole()
        );
    }
}

