package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.Task;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;
import com.example.TaskManagement.repository.TasksRepository;
import com.example.TaskManagement.service.ProjectService;
import com.example.TaskManagement.service.TaskService;
import com.example.TaskManagement.service.UserProjectMappingService;
import com.example.TaskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private final ProjectService projectService;
    private final UserService userService;
    private final UserProjectMappingService userProjectMappingService;

    /**
     * Constructs a new DefaultTaskService with required services and repositories.
     *
     * @param taskRepository Repository for task data access.
     * @param projectService Service for managing project-related operations.
     * @param userService Service for managing user-related operations.
     * @param userProjectMappingService Service for managing mappings between users and projects.
     */
    @Autowired
    public DefaultTaskService(TasksRepository taskRepository, ProjectService projectService, UserService userService, UserProjectMappingService userProjectMappingService) {
        this.taskRepository = taskRepository;
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
        Project project = projectService.getProjectById(task.getProject().getId());
        User user = userService.getUserById(task.getAssignedToUser().getId());
        if (project != null && user != null) {
            userProjectMappingService.addUserToProject(user.getId(), project.getId());

        }

        if (project != null) {
            if (project.getDeadline().before(new Date())) {
                return null;
            }
            projectService.notifyUsers(project, ProjectModification.PROJECT_ADDED_TASK);

            return taskRepository.save(task);
        }
        return null;

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
}
