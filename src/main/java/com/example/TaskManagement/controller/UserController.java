package com.example.TaskManagement.controller;

import com.example.TaskManagement.DTO.UserDTO;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing user-related endpoints.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    @Qualifier("defaultUserService")
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Retrieves all users from the system.
     *
     * @return A list of all users.
     */


    @GetMapping("/get")
    @ResponseBody
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    /**
     * Saves a new user in the system.
     *
     * @param userDTO The user object to save.
     * @return The saved user.
     */
    @PostMapping("/signup")
    @ResponseBody
    public User saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    /**
     * Deletes a user from the system by their unique ID.
     *
     * @param userId The ID of the user to delete.
     * @return ResponseEntity with a message indicating successful deletion.
     */
    @DeleteMapping("/delete/{userId}")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted");
    }

    /**
     * Updates an existing user in the system.
     *
     * @param userDTO The user object with updated information.
     * @return The updated user.
     */
    @PutMapping("/put")
    @ResponseBody
    public User updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }
}
