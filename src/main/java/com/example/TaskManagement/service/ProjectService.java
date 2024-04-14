package com.example.TaskManagement.service;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.enums.ProjectModification;
import com.example.TaskManagement.observer.ProjectObservable;

import java.util.List;
import java.util.Map;

public interface ProjectService {



    List<Project> getAllProjects();

    Project getProjectById(int projectId);


    Project createProject(Project project);

    Project updateProject(int projectId, Project project);
    void deleteProject(int projectId);
    void notifyUsers(Project project, ProjectModification projectModification);


}
