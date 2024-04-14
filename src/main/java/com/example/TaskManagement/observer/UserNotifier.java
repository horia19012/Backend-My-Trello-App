package com.example.TaskManagement.observer;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;

import java.text.SimpleDateFormat;

public class UserNotifier implements ProjectsObserver {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserNotifier() {
    }

    public UserNotifier(User user) {
        this.user = user;
    }

    @Override
    public void update(Project project, ProjectModification projectModification) {

        System.out.print("User " + user.getUsername() + " notified for project " + project.getProjectName() +
                " that ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        switch (projectModification) {
            case PROJECT_DEADLINE_REACHED: {
                System.out.println("Project deadline reached");
                break;
            }
            case PROJECT_DEADLINE_CHANGED: {
                System.out.println("Project deadline changed. New deadline: " + dateFormat.format(project.getDeadline()));
                break;
            }
            case PROJECT_DESCRIPTION_MODIFICATION: {
                System.out.println("Project description modified");
                break;
            }
            case PROJECT_OWNER_CHANGED: {
                System.out.println("Project owner changed");
                break;
            }
            case PROJECT_ADDED_TASK: {
                System.out.println("Task added");
                break;
            }
            default: {
                System.out.println("Unsupported project modification");
                break;
            }
        }

    }
}
