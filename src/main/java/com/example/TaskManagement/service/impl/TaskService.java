package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAllTasks();

    Optional<Task> getTaskById(int taskId);

    Task createTask(Task task);

    Task updateTask(int taskId, Task task);

    void deleteTask(int taskId);

}
