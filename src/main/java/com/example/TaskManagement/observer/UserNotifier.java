package com.example.TaskManagement.observer;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.enums.ProjectModification;

import java.text.SimpleDateFormat;

public class UserNotifier implements ProjectsObserver {

    @Override
    public void update(Project project, ProjectModification projectModification) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        switch (projectModification) {
            case PROJECT_DEADLINE_REACHED: {
                System.out.println("Project deadline reached for project " + project.getProjectName());
                break;
            }
            case PROJECT_DEADLINE_CHANGED:{
                System.out.println("Project deadline changed for project " + project.getProjectName() +
                        ". New deadline: " + dateFormat.format(project.getDeadline()));
                break;
            }
            case PROJECT_DESCRIPTION_MODIFICATION: {
                System.out.println("Project description modified for project " + project.getProjectName());
                break;
            }
            case PROJECT_OWNER_CHANGED: {
                System.out.println("Project owner changed for project " + project.getProjectName());
                break;
            }
            case PROJECT_ADDED_TASK: {
                System.out.println("Task added to project " + project.getProjectName());
                break;
            }
            default: {
                System.out.println("Unsupported project modification");
                break;
            }
        }
    }
}
