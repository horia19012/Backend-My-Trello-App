package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.Task;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;
import com.example.TaskManagement.repository.TasksRepository;
import com.example.TaskManagement.repository.UsersRepository;
import com.example.TaskManagement.service.ProjectService;
import com.example.TaskManagement.service.TaskService;
import com.example.TaskManagement.service.UserProjectMappingService;
import com.example.TaskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
/**
 * Service implementation for managing tasks.
 * This class provides methods to manage task lifecycle operations such as creation, update, deletion, and retrieval.
 */
@Service
public class DefaultTaskService implements TaskService {

    private final TasksRepository taskRepository;

    private final UsersRepository usersRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final UserProjectMappingService userProjectMappingService;

    /**
     * Constructs a new DefaultTaskService with required services and repositories.
     *
     * @param taskRepository            Repository for task data access.
     * @param usersRepository
     * @param projectService            Service for managing project-related operations.
     * @param userService               Service for managing user-related operations.
     * @param userProjectMappingService Service for managing mappings between users and projects.
     */
    @Autowired
    public DefaultTaskService(TasksRepository taskRepository, UsersRepository usersRepository, ProjectService projectService, UserService userService, UserProjectMappingService userProjectMappingService) {
        this.taskRepository = taskRepository;
        this.usersRepository = usersRepository;
        this.projectService = projectService;
        this.userService = userService;
        this.userProjectMappingService = userProjectMappingService;
    }
    /**
     * Retrieves all tasks.
     *
     * @return a list of all tasks.
     */
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    /**
     * Retrieves a task by its ID.
     *
     * @param taskId the ID of the task to retrieve.
     * @return an Optional containing the task if found, or an empty Optional if not found.
     */
    @Override
    public Optional<Task> getTaskById(int taskId) {
        return taskRepository.findById(taskId);
    }
    /**
     * Creates a new task and assigns it to a project and a user.
     *
     * @param task the task to be created.
     * @return the created task, or null if the project does not exist or the project deadline has passed.
     */

    @Override
    public Task createTask(Task task) {
        // Retrieve the project
        Project project = projectService.getProjectById(task.getProject().getId());

        // Retrieve the user only if assignedToUser is not null
        User user = null;
        if (task.getAssignedToUser() != null) {
            user = userService.getUserById(task.getAssignedToUser().getId());
        }

        // If the project and user (if not null) are valid, proceed with adding the user to the project
        if (project != null && (user != null || task.getAssignedToUser() == null)) {
            if (user != null) {
                userProjectMappingService.addUserToProject(user.getId(), project.getId());
            }

            // Check if the project's deadline has passed
            if (project.getDeadline().before(new Date())) {
                return null; // Do not save the task if the project's deadline has passed
            }

            // Notify users about the new task in the project
            projectService.notifyUsers(project, ProjectModification.PROJECT_ADDED_TASK);

            // Save and return the task
            return taskRepository.save(task);
        }

        return null; // Return null if the project is not found or invalid user
    }

    /**
     * Updates an existing task.
     *
     * @param taskId the ID of the task to update.
     * @param updatedTask the task with updated fields.
     * @return the updated task, or null if the task does not exist or the project does not exist.
     */
    @Override
    public Task updateTask(int taskId, Task updatedTask) {

        if (!taskRepository.existsById(taskId)) {
            return null;
        }

        updatedTask.setId(taskId);

        Project project = projectService.getProjectById(updatedTask.getProject().getId());
        User user = userService.getUserById(updatedTask.getAssignedToUser().getId());
        if (project != null && user != null) {
            userProjectMappingService.addUserToProject(user.getId(), project.getId());
        }

        if (project == null) {
            return null;
        }

        return taskRepository.save(updatedTask);
    }


    /**
     * Deletes a task by its ID.
     *
     * @param taskId the ID of the task to delete.
     */
    @Override
    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }
    /**
     * Retrieves all tasks assigned to a specific user.
     *
     * @param userId the ID of the user whose tasks are to be retrieved.
     * @return a list of tasks assigned to the specified user.
     */

    @Override
    public List<Task> getTasksByUser(int userId) {
        return taskRepository.findByAssignedToUserId(userId);
    }

    /**
     * Retrieves all tasks associated with a specific project.
     *
     * @param projectId the ID of the project whose tasks are to be retrieved.
     * @return a list of tasks associated with the specified project.
     */
    @Override
    public List<Task> getTasksByProject(int projectId) {
       return taskRepository.findByProjectId(projectId);
    }


    public List<Task> getTasksByUsername(String username) {
        Optional<User> user = usersRepository.findByUsername(username);
        if (user.isPresent()) {
            return taskRepository.findByAssignedToUserId(user.get().getId());
        } else {
            return new ArrayList<>(); // or handle this case as needed
        }
    }
}
