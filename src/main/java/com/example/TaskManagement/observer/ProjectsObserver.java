package com.example.TaskManagement.observer;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.enums.ProjectModification;

public interface ProjectsObserver {
    void update(Project project, ProjectModification projectModification);
}
