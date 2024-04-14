package com.example.TaskManagement.repository;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Task, Integer> {

    List<Task> findByAssignedToUserId(int userId);
    List<Task> findByProjectId(int projectId);
}
