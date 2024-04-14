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

@Service
public class DefaultTaskService implements TaskService {

    private final TasksRepository taskRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final UserProjectMappingService userProjectMappingService;

    @Autowired
    public DefaultTaskService(TasksRepository taskRepository, ProjectService projectService, UserService userService, UserProjectMappingService userProjectMappingService) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
        this.userService = userService;
        this.userProjectMappingService = userProjectMappingService;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(int taskId) {
        return taskRepository.findById(taskId);
    }

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

    @Override
    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> getTasksByUser(int userId) {
        return taskRepository.findByAssignedToUserId(userId);
    }

    @Override
    public List<Task> getTasksByProject(int projectId) {
       return taskRepository.findByProjectId(projectId);
    }
}
