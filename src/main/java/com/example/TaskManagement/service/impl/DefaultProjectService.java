package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.repository.ProjectsRepository;
import com.example.TaskManagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("defaultProjectService")
public class DefaultProjectService implements ProjectService {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectsRepository.findAll();
    }

    @Override
    public Project getProjectById(int projectId) {
        Optional<Project> projectOptional = projectsRepository.findById(projectId);
        return projectOptional.orElse(null);
    }



    @Override
    public Project createProject(Project project) {
        return projectsRepository.save(project);
    }

    @Override
    public Project updateProject(int projectId, Project project) {
        Optional<Project> existingProjectOptional = projectsRepository.findById(projectId);
        if (existingProjectOptional.isPresent()) {
            project.setId(projectId);
            return projectsRepository.save(project);
        }
        return null;
    }

    @Override
    public void deleteProject(int projectId) {
        projectsRepository.deleteById(projectId);
    }


}
