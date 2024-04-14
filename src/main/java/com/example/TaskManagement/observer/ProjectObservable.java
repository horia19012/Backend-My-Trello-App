package com.example.TaskManagement.observer;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an observable object for project modifications.
 */
public class ProjectObservable {

    private List<UserNotifier> userNotifiers;

    /**
     * Default constructor.
     */
    public ProjectObservable() {
        this.userNotifiers = new ArrayList<>();
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param userNotifier The observer to be added.
     */
    public void addObserver(UserNotifier userNotifier) {
        this.userNotifiers.add(userNotifier);
    }

    /**
     * Deletes an observer from the list of observers.
     *
     * @param userNotifier The observer to be deleted.
     */
    public void deleteObserver(UserNotifier userNotifier) {
        this.userNotifiers.remove(userNotifier);
    }

    /**
     * Notifies all observers about the modification in the project.
     *
     * @param project            The project that has been modified.
     * @param projectModification The type of modification occurred in the project.
     */
    public void updateProject(Project project, ProjectModification projectModification) {
        for (UserNotifier userNotifier : userNotifiers) {
            userNotifier.update(project, projectModification);
        }
    }

}

