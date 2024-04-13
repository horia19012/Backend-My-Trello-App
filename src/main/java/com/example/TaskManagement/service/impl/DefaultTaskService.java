package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.entity.Task;
import com.example.TaskManagement.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultTaskService implements TaskService {

    private final TasksRepository taskRepository;

    @Autowired
    public DefaultTaskService(TasksRepository taskRepository) {
        this.taskRepository = taskRepository;
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
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(int taskId, Task updatedTask) {
        if (!taskRepository.existsById(taskId)) {
            return null;
        }
        updatedTask.setId(taskId);
        return taskRepository.save(updatedTask);
    }

    @Override
    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }
}
