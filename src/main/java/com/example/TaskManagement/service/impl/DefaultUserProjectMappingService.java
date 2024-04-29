package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.UserProjectMapping;
import com.example.TaskManagement.repository.ProjectsRepository;
import com.example.TaskManagement.repository.UserProjectMappingRepository;
import com.example.TaskManagement.service.UserProjectMappingService;

import com.example.TaskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DefaultUserProjectMappingService implements UserProjectMappingService {

    private final UserProjectMappingRepository userProjectMappingRepository;
    private final ProjectsRepository projectsRepository;
    private final UserService userService;
    @Autowired
    public DefaultUserProjectMappingService(UserProjectMappingRepository userProjectMappingRepository, ProjectsRepository projectsRepository, UserService userService) {
        this.userProjectMappingRepository = userProjectMappingRepository;
        this.projectsRepository = projectsRepository;
        this.userService = userService;
    }

    @Override
    public List<Project> getProjectsByUser(int userId) {
        List<UserProjectMapping> mappings = userProjectMappingRepository.findAll();
        List<Project> projects = new ArrayList<>();
        for (UserProjectMapping mapping :
                mappings) {
            if (mapping.getUserId() == userId) {
                if (projectsRepository.findById(mapping.getProjectId()).isPresent()){
                    projects.add(projectsRepository.findById(mapping.getProjectId()).get());
                }
            }
        }
        return projects;
    }

    @Override
    public Set<User> getUsersByProject(int projectId) {
        List<UserProjectMapping> mappings = userProjectMappingRepository.findAll();
        Set<User> users = new HashSet<>();
        for (UserProjectMapping mapping : mappings) {
            if (mapping.getProjectId() == projectId) {
                User user = userService.getUserById(mapping.getUserId());
                if (user != null) {
                    users.add(user);
                }
            }
        }
        return users;
    }

    @Override
    public void addUserToProject(int userId, int projectId) {
        UserProjectMapping userProjectMapping = new UserProjectMapping();
        userProjectMapping.setProjectId(projectId);
        userProjectMapping.setUserId(userId);
        userProjectMappingRepository.save(userProjectMapping);
    }

    @Override
    public void removeUserFromProject(int userId, int projectId) {
        userProjectMappingRepository.delete(userProjectMappingRepository.findByUserIdAndProjectId(userId, projectId));
    }

}
