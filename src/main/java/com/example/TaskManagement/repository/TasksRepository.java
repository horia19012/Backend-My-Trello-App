package com.example.TaskManagement.repository;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task, Integer> {
}
