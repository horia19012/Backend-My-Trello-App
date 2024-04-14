package com.example.TaskManagement.observer;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProjectObservable {

    private List<UserNotifier> userNotifiers;

    public ProjectObservable() {
        this.userNotifiers = new ArrayList<>();
    }

    public void addObserver(UserNotifier userNotifier) {
        this.userNotifiers.add(userNotifier);
    }

    public void deleteObserver(UserNotifier userNotifier) {
        this.userNotifiers.remove(userNotifier);
    }

    public void updateProject(Project project, ProjectModification projectModification) {
        for (UserNotifier userNotifier : userNotifiers) {
            userNotifier.update(project, projectModification);
        }
    }

}
