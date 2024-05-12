package com.example.TaskManagement.controller;

import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.entity.UserProjectMapping;
import com.example.TaskManagement.service.UserProjectMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controller for managing mappings between users and projects.
 * This class handles API requests to add, remove, and retrieve users associated with specific projects.
 */
@RestController
@RequestMapping("/user-project-mappings")
public class UserProjectMappingController {

    private final UserProjectMappingService userProjectMappingService;

    /**
     * Autowires the UserProjectMappingService to enable operations on user-project mappings.
     * @param userProjectMappingService The service layer managing user-project relationship logic.
     */
    @Autowired
    public UserProjectMappingController(UserProjectMappingService userProjectMappingService) {
        this.userProjectMappingService = userProjectMappingService;
    }

    /**
     * Retrieves all users associated with a specific project.
     *
     * @param projectId The ID of the project whose users are to be retrieved.
     * @return ResponseEntity containing a set of users linked to the specified project and HTTP status OK.
     */
    @GetMapping("/{projectId}/users")
    public ResponseEntity<Set<User>> getUsersByProject(@PathVariable int projectId) {
        Set<User> users = userProjectMappingService.getUsersByProject(projectId);
        return ResponseEntity.ok(users);
    }

    /**
     * Adds a user to a specific project by creating a mapping between the user and the project.
     *
     * @param userId The ID of the user to add.
     * @param projectId The ID of the project to which the user will be added.
     * @return ResponseEntity with HTTP status CREATED to indicate successful addition.
     */
    @PostMapping("/{userId}/{projectId}")
    public ResponseEntity<Void> addUserToProject(@PathVariable int userId, @PathVariable int projectId) {
        userProjectMappingService.addUserToProject(userId, projectId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Removes a user from a project by deleting the mapping between the user and the project.
     *
     * @param userId The ID of the user to be removed.
     * @param projectId The ID of the project from which the user will be removed.
     * @return ResponseEntity with HTTP status NO_CONTENT to indicate successful removal.
     */
    @DeleteMapping("/{userId}/{projectId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable int userId, @PathVariable int projectId) {
        userProjectMappingService.removeUserFromProject(userId, projectId);
        return ResponseEntity.noContent().build();
    }
}
