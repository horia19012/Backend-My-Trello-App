package com.example.TaskManagement.service;

import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.entity.Project;

import java.util.List;
import java.util.Set;

public interface UserProjectMappingService {

    List<Project> getProjectsByUser(int userId);

    Set<User> getUsersByProject(int projectId);

    void addUserToProject(int userId, int projectId);

    void removeUserFromProject(int userId, int projectId);

}
