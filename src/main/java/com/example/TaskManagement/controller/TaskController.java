package com.example.TaskManagement.controller;

import com.example.TaskManagement.entity.Task;
import com.example.TaskManagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling RESTful API requests related to tasks.
 */
@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Constructor injecting TaskService dependency.
     *
     * @param taskService The TaskService instance to be injected.
     */
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * GET endpoint to retrieve all tasks.
     *
     * @return ResponseEntity containing a list of tasks and HttpStatus OK.
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    /**
     * GET endpoint to retrieve a task by its ID.
     *
     * @param id The ID of the task to retrieve.
     * @return ResponseEntity containing the requested task and HttpStatus OK if found, otherwise HttpStatus NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") int id) {
        Optional<Task> taskOptional = taskService.getTaskById(id);
        return taskOptional.map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * POST endpoint to create a new task.
     *
     * @param task The task object to be created.
     * @return ResponseEntity containing the created task and HttpStatus CREATED.
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    /**
     * PUT endpoint to update an existing task.
     *
     * @param id   The ID of the task to be updated.
     * @param task The updated task object.
     * @return ResponseEntity containing the updated task and HttpStatus OK if updated successfully, otherwise HttpStatus NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") int id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE endpoint to delete a task by its ID.
     *
     * @param id The ID of the task to be deleted.
     * @return ResponseEntity with HttpStatus NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") int id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * GET endpoint to retrieve tasks by user ID.
     *
     * @param userId The ID of the user.
     * @return ResponseEntity containing a list of tasks belonging to the specified user and HttpStatus OK.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable int userId) {
        List<Task> tasks = taskService.getTasksByUser(userId);
        return ResponseEntity.ok().body(tasks);
    }

    /**
     * GET endpoint to retrieve tasks by project ID.
     *
     * @param projectId The ID of the project.
     * @return ResponseEntity containing a list of tasks belonging to the specified project and HttpStatus OK.
     */
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable int projectId) {
        List<Task> tasks = taskService.getTasksByProject(projectId);
        return ResponseEntity.ok().body(tasks);
    }
}

