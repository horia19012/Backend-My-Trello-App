package com.example.TaskManagement.observer;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.enums.ProjectModification;

public interface ProjectsObserver {

    /**
     * Method called when a project is modified.
     *
     * @param project            The project that has been modified.
     * @param projectModification The type of modification occurred in the project.
     */
    void update(Project project, ProjectModification projectModification);
}