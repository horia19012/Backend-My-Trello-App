package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.repository.UsersRepository;
import com.example.TaskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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


    public DefaultUserService(UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }

    /**
     * Retrieves all users from the database.
     * @return A list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }




    /**
     * Updates the information of a user.
     * @param user The user object containing the updated information.
     * @return The updated user object.
     */
    public User updateUser(User user) {
        int id = user.getId();
        User foundUser = getUserById(id);
        if (foundUser != null) {
            return saveUser(user); // If user is found, update the user
        }
        return null; // If user is not found, return null
    }

    /**
     * Retrieves a user by their ID.
     * @param userId The ID of the user to retrieve.
     * @return The user object if found, otherwise null.
     */
    @Override
    public User getUserById(int userId) {
        Optional<User> userOptional= usersRepository.findById(userId);
        return userOptional.orElse(null);
    }

    /**
     * Saves a new user or updates an existing one.
     * @param user The user object to save or update.
     * @return The saved or updated user object.
     */
    @Override
    public User saveUser(User user) {
        usersRepository.save(user);
        return user;
    }

    /**
     * Deletes a user by their ID.
     * @param userId The ID of the user to delete.
     */
    @Override
    public void deleteUser(int userId) {
        usersRepository.deleteById(userId);
    }
}
