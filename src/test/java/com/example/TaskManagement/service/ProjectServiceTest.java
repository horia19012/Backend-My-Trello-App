package com.example.TaskManagement.service;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;
import com.example.TaskManagement.observer.ProjectObservable;
import com.example.TaskManagement.observer.UserNotifier;
import com.example.TaskManagement.repository.ProjectsRepository;
import com.example.TaskManagement.repository.UserProjectMappingRepository;
import com.example.TaskManagement.service.impl.DefaultProjectService;
import com.example.TaskManagement.service.impl.DefaultUserProjectMappingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {
    @Mock
    private ProjectsRepository projectRepoMock;

    @Mock
    private UserProjectMappingRepository userProjectMappingRepoMock;

    @Mock
    private ProjectObservable projectObservable;

    private DefaultProjectService projectService;
    private UserService userService;
    @Mock
    private UserProjectMappingService userProjectMappingServiceMock;

    private static final int ID = 1;

    private Project project=new Project("Project 1", "Description 1");


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = mock(UserService.class);
        userProjectMappingServiceMock = mock(DefaultUserProjectMappingService.class);
        this.projectService = new DefaultProjectService(userProjectMappingServiceMock,projectRepoMock,projectObservable);


    }

    @Test
    public void shouldReturnAllProjects() {
        // Given
        List<Project> projects = Arrays.asList(
                new Project("Project 1", "Description 1"),
                new Project("Project 2", "Description 2")
        );
        when(projectRepoMock.findAll()).thenReturn(projects);

        // When
        List<Project> allProjects = projectService.getAllProjects();

        // Then
        assertEquals(2, allProjects.size());
        assertEquals(projects, allProjects);
    }

    @Test
    public void shouldReturnProjectById() {
        // Given
        Project project = new Project("Project 1", "Description 1");
        project.setId(1);
        when(projectRepoMock.findById(1)).thenReturn(Optional.of(project));

        // When
        Project foundProject = projectService.getProjectById(1);

        // Then
        assertNotNull(foundProject);
        assertEquals(project, foundProject);
    }
    @Test
    public void shouldReturnNull_ifProjectNotFoundById() {
        // Given
        when(projectRepoMock.findById(1)).thenReturn(Optional.empty());

        // When
        Project foundProject = projectService.getProjectById(1);

        // Then
        assertNull(foundProject);
    }

    @Test
    public void shouldCreateProject() {
        // Given
        Project project = new Project("Project 1", "Description 1");

        // When
        when(projectRepoMock.save(project)).thenReturn(project);
        Project createdProject = projectService.createProject(project);

        // Then
        assertNotNull(createdProject);
        assertEquals(project, createdProject);
        verify(projectRepoMock, times(1)).save(project);
    }

    @Test
    public void notifyUsers_whenDescriptionModification() {
        // Given
        Project existingProject = new Project("Project Name", "Project Description");
        existingProject.setId(ID);
        Project updatedProject = new Project("Project Name", "Updated Description");
        updatedProject.setId(ID);

        // Mock the behavior of projects repository to return the existing project
        doReturn(Optional.of(existingProject)).when(projectRepoMock).findById(ID);

        // When
        projectService.updateProject(ID, updatedProject);

        // Then
        verify(projectObservable, times(1)).updateProject(existingProject,ProjectModification.PROJECT_DESCRIPTION_MODIFICATION);
    }

    @Test
    public void notifyUsers_whenDeadlineChanged() {
        // Given
        Date existingDeadline = new Date();
        Date updatedDeadline = new Date(existingDeadline.getTime() + 1000); // Updated deadline is different from existing
        Project existingProject = new Project("Project Name", "Project Description");
        existingProject.setId(ID);
        existingProject.setDeadline(existingDeadline);
        Project updatedProject = new Project("Project Name", "Project Description");
        updatedProject.setId(ID);
        updatedProject.setDeadline(updatedDeadline);

        // Mock the behavior of projects repository to return the existing project
        doReturn(Optional.of(existingProject)).when(projectRepoMock).findById(ID);

        // When
        projectService.updateProject(ID, updatedProject);

        // Then
        verify(projectObservable, times(1)).updateProject(existingProject,ProjectModification.PROJECT_DEADLINE_CHANGED);
    }

    @Test
    public void notifyUsers_whenOwnerChanged() {
        // Given
        User existingOwner = new User("existingOwner");
        existingOwner.setId(1);
        User newOwner = new User("newOwner");
        newOwner.setId(2);

        Project existingProject = new Project("Project Name", "Project Description", existingOwner);
        existingProject.setId(ID);
        Project updatedProject = new Project("Project Name", "Project Description", newOwner);
        updatedProject.setId(ID);

        Date existingDeadline = new Date();

        existingProject.setOwner(existingOwner);
        updatedProject.setOwner(newOwner);
        existingProject.setDeadline(existingDeadline);
        updatedProject.setDeadline(existingDeadline);

        // Mock the behavior of projects repository to return the existing project
        doReturn(Optional.of(existingProject)).when(projectRepoMock).findById(ID);
        // Mock the behavior of userService to return the existing owner and new owner
        doReturn(existingOwner).when(userService).getUserById(existingOwner.getId());
        doReturn(newOwner).when(userService).getUserById(newOwner.getId());

        // When
        projectService.updateProject(ID, updatedProject);

        // Then
        verify(projectObservable, times(1)).updateProject(existingProject,ProjectModification.PROJECT_OWNER_CHANGED);
    }

    @Test
    public void createAndPopulateUserNotifiers(){
        //given
        User user1=new User("user1","fullname1","email1");
        User user2=new User("user2","fullname2","email2");
        user2.setId(ID);
        Set<User> users=Set.of(user1,user2);
        ProjectObservable thisProjectObservable=new ProjectObservable();
        projectService.setProjectObservable(thisProjectObservable);
        doReturn(users).when(userProjectMappingServiceMock).getUsersByProject(ID);

        //when
        projectService.notifyUsers(project,ProjectModification.PROJECT_DESCRIPTION_MODIFICATION);

        //then
        List<UserNotifier> userNotifiers = thisProjectObservable.getUserNotifiers();
        if (userNotifiers.size() == 2) {
            assertEquals(user1, userNotifiers.get(0).getUser());
            assertEquals(user2, userNotifiers.get(0).getUser());
        }
    }







    @Test
    public void shouldReturnNull_ifProjectToUpdateNotFound() {
        // Given
        Project updatedProject = new Project("Updated Project 1", "Updated Description 1");
        updatedProject.setId(1);
        when(projectRepoMock.findById(1)).thenReturn(Optional.empty());

        // When
        Project result = projectService.updateProject(1, updatedProject);

        // Then
        assertNull(result);

    }

    @Test
    public void shouldDeleteProject() {
        // Given
        int projectId = 1;

        // When
        projectService.deleteProject(projectId);

        // Then
        verify(projectRepoMock, times(1)).deleteById(projectId);
    }

    @Test
    public void notifyAllUsers_whenProjectModification() {
        // Given
        int projectId = 1;
        project.setId(projectId);

        Set<User> users = new HashSet<>();
        User user1 = new User("user1", "User One", "user1@example.com");
        user1.setId(1);
        User user2 = new User("user2", "User Two", "user2@example.com");
        user2.setId(2);
        users.add(user1);
        users.add(user2);

        // Mocking the behavior of the userProjectMappingService
        when(userProjectMappingServiceMock.getUsersByProject(projectId)).thenReturn(users);

        // When
        projectService.notifyUsers(project, ProjectModification.PROJECT_DESCRIPTION_MODIFICATION);

        // Then
        // Verify that for each user, a UserNotifier is added as an observer to the projectObservable
        verify(projectObservable, times(2)).addObserver(any(UserNotifier.class));

        // Verify that the projectObservable's updateProject method is called with the correct project and modification
        verify(projectObservable).updateProject(project, ProjectModification.PROJECT_DESCRIPTION_MODIFICATION);
    }



}
