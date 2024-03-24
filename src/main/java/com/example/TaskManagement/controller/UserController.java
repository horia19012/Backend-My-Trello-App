package com.example.TaskManagement.controller;

import com.example.TaskManagement.entity.DeleteUserDTO;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    @Qualifier("defaultUserService")
    private UserService userService;

    @GetMapping("/get")
    @ResponseBody
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping("/save")
    @ResponseBody
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @DeleteMapping("/delete/{userId}")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("user deleted");
    }
    @PutMapping("/put")
    @ResponseBody
    public User updateUser(@RequestBody User user){
        userService.updateUser(user);
        return user;
    }
}
