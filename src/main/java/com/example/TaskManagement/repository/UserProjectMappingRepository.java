package com.example.TaskManagement.repository;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.UserProjectMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserProjectMappingRepository extends JpaRepository<UserProjectMapping, Integer> {

    UserProjectMapping findByUserIdAndProjectId(int userId, int projectId);


    Set<Project> findProjectsByUserId(int userId);
}
