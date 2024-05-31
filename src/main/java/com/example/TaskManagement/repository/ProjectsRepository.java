package com.example.TaskManagement.repository;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<Project, Integer> {



    List<Project> findByOwner_Id(int ownerId);
}
