package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.Task;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;
import com.example.TaskManagement.observer.ProjectObservable;
import com.example.TaskManagement.observer.UserNotifier;
import com.example.TaskManagement.repository.ProjectsRepository;
import com.example.TaskManagement.service.ProjectService;
import com.example.TaskManagement.service.TaskService;
import com.example.TaskManagement.service.UserProjectMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.TaskManagement.enums.ProjectModification.*;

@Service("defaultProjectService")
public class DefaultProjectService implements ProjectService {

    @Autowired
    private ProjectsRepository projectsRepository;
    @Autowired
    private UserProjectMappingService userProjectMappingService;
    private ProjectObservable projectObservable = new ProjectObservable();

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
            Project existingProject = existingProjectOptional.get();
            if (existingProject.getProjectDescription().equals(project.getProjectDescription())) {
                notifyUsers(project, PROJECT_DESCRIPTION_MODIFICATION);
            } else if (existingProject.getDeadline().equals(project.getDeadline())) {
                notifyUsers(project, PROJECT_DEADLINE_CHANGED);
            }else if (existingProject.getOwner()!=project.getOwner()){
                notifyUsers(project, PROJECT_OWNER_CHANGED);
            }

            return projectsRepository.save(project);
        }

        return null;
    }

    @Override
    public void deleteProject(int projectId) {
        projectsRepository.deleteById(projectId);
    }

    public void notifyUsers(Project project, ProjectModification projectModification) {
        Set<User> users = userProjectMappingService.getUsersByProject(project.getId());
        users.forEach(user -> projectObservable.addObserver(new UserNotifier(user)));
        projectObservable.updateProject(project, projectModification);
    }

}
