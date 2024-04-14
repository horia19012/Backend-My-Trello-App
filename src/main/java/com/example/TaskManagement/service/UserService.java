package com.example.TaskManagement.service;

import com.example.TaskManagement.entity.User;

import java.util.List;

/**
 * Service interface for managing users in the system.
 */
public interface UserService {

    /**
     * Retrieves all users from the system.
     * @return A list of all users.
     */
    List<User> getAllUsers();

    /**
     * Updates an existing user in the system.
     * @param user The user object with updated information.
     * @return The updated user.
     */
    User updateUser(User user);

    /**
     * Retrieves a user by their unique ID.
     * @param userId The ID of the user to retrieve.
     * @return The user object if found, otherwise null.
     */
    User getUserById(int userId);

    /**
     * Saves a new user or updates an existing one in the system.
     * @param user The user object to save or update.
     * @return The saved or updated user.
     */
    User saveUser(User user);

    /**
     * Deletes a user from the system by their unique ID.
     * @param userId The ID of the user to delete.
     */
    void deleteUser(int userId);
}
