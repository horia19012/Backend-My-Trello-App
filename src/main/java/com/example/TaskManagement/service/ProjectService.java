package com.example.TaskManagement.service;

import com.example.TaskManagement.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    Project getProjectById(int projectId);


    Project createProject(Project project);

    Project updateProject(int projectId, Project project);



    void deleteProject(int projectId);


}
