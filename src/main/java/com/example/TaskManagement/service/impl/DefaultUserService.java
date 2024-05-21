package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.DTO.UserDTO;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.exception.DuplicateException;
import com.example.TaskManagement.repository.UsersRepository;
import com.example.TaskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Default implementation of the UserService interface.
 */
@Service("defaultUserService")
public class DefaultUserService implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;


    @Autowired
    public DefaultUserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }


    /**
     * Updates the information of a user.
     *
     * @param userDTO The user object containing the updated information.
     * @return The updated user object.
     */
    @Override
    public User updateUser(UserDTO userDTO) {
        String username = userDTO.getUsername();
        User foundUser = getUserByUsername(username);
        if (foundUser != null) {

            userDTO.setId(foundUser.getId());
            return saveUser(userDTO);
        }
        return null;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The user object if found, otherwise null.
     */
    @Override
    public User getUserById(int userId) {
        Optional<User> userOptional = usersRepository.findById(userId);
        return userOptional.orElse(null);
    }

    /**
     * Saves a new user or updates an existing one.
     *
     * @param userDTO The user object to save or update.
     * @return The saved or updated user object.
     */
    @Override
    public User saveUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getUsername(),
                userDTO.getFullName(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );

        if (usersRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateException("Email already exists: " + userDTO.getEmail());
        }
        if (usersRepository.existsByUsername(userDTO.getUsername())) {
            throw new DuplicateException("Username already exists: " + userDTO.getUsername());
        }

        if (userDTO.getId() != 0) {
            user.setId(userDTO.getId());
        }

        usersRepository.save(user);
        return user;
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId The ID of the user to delete.
     */
    @Override
    public void deleteUser(int userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = usersRepository.findByUsername(username);
        return userOptional.orElse(null);
    }

}
