package com.example.TaskManagement.observer;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;

import java.text.SimpleDateFormat;

/**
 * Class responsible for notifying users about project modifications.
 */
public class UserNotifier implements ProjectsObserver {
    private User user;

    /**
     * Retrieves the user associated with the notifier.
     *
     * @return The user associated with the notifier.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the notifier.
     *
     * @param user The user to be associated with the notifier.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Default constructor.
     */
    public UserNotifier() {
    }

    /**
     * Parameterized constructor.
     *
     * @param user The user to be associated with the notifier.
     */
    public UserNotifier(User user) {
        this.user = user;
    }

    /**
     * Method called when a project is modified, notifying the user about the modification.
     *
     * @param project            The project that has been modified.
     * @param projectModification The type of modification occurred in the project.
     */
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
