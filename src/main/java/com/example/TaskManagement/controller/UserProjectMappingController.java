package com.example.TaskManagement.controller;

import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.entity.UserProjectMapping;
import com.example.TaskManagement.service.UserProjectMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user-project-mappings")
public class UserProjectMappingController {

    private final UserProjectMappingService userProjectMappingService;

    @Autowired
    public UserProjectMappingController(UserProjectMappingService userProjectMappingService) {
        this.userProjectMappingService = userProjectMappingService;
    }

    @GetMapping("/{projectId}/users")
    public ResponseEntity<Set<User>> getUsersByProject(@PathVariable int projectId) {
        Set<User> users = userProjectMappingService.getUsersByProject(projectId);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{userId}/{projectId}")
    public ResponseEntity<Void> addUserToProject(@PathVariable int userId, @PathVariable int projectId) {
        userProjectMappingService.addUserToProject(userId, projectId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{userId}/{projectId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable int userId, @PathVariable int projectId) {
        userProjectMappingService.removeUserFromProject(userId, projectId);
        return ResponseEntity.noContent().build();
    }
}