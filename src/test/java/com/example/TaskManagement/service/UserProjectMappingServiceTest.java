package com.example.TaskManagement.service;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.entity.UserProjectMapping;
import com.example.TaskManagement.repository.ProjectsRepository;
import com.example.TaskManagement.repository.UserProjectMappingRepository;
import com.example.TaskManagement.service.impl.DefaultUserProjectMappingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Tests for the DefaultUserProjectUserMappingService class, focusing on the service's ability to manage mappings
 * between users and projects effectively. These tests ensure the core functionality for adding, removing,
 * and retrieving user-project mappings is working as expected.
 */
public class UserProjectMappingServiceTest {

    @Mock
    private UserProjectMappingRepository userProjectMappingRepositoryMock;

    @Mock
    private ProjectsRepository projectsRepositoryMock;

    @Mock
    private UserService userServiceMock;

    private DefaultUserProjectMappingService userProjectMappingService;

    private int projectId = 1;

    /**
     * Sets up the environment for each test, initializing mocks and the service under test.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userProjectMappingService = new DefaultUserProjectMappingService(
                userProjectMappingRepositoryMock, projectsRepositoryMock, userServiceMock);
    }

    /**
     * Tests the ability to retrieve all projects associated with a specific user.
     * Verifies that the correct list of projects is returned for a given user ID.
     */
    @Test
    public void shouldGetProjectsByUser() {
        // Given
        int userId = 1;
        List<UserProjectMapping> mappings = Arrays.asList(
                new UserProjectMapping(userId, 1),
                new UserProjectMapping(userId, 2));
        when(userProjectMappingRepositoryMock.findAll()).thenReturn(mappings);

        Project project1 = new Project("Project 1", "Description 1");
        Project project2 = new Project("Project 2", "Description 2");
        when(projectsRepositoryMock.findById(1)).thenReturn(Optional.of(project1));
        when(projectsRepositoryMock.findById(2)).thenReturn(Optional.of(project2));

        // When
        List<Project> projects = userProjectMappingService.getProjectsByUser(userId);

        // Then
        assertEquals(2, projects.size());
    }

    /**
     * Tests the ability to retrieve all users associated with a specific project.
     * Verifies that the correct set of users is returned for a given project ID.
     */
    @Test
    public void shouldGetUsersByProject_whenProjectIdExisting() {
        // Given
        User user1 = new User("user1", "fullname1", "email1");
        user1.setId(1);
        User user2 = new User("user2", "fullname2", "email2");
        user2.setId(2);

        Set<User> expectedUsers = new HashSet<>(Arrays.asList(user1, user2));

        List<UserProjectMapping> mappings = Arrays.asList(
                new UserProjectMapping(1, projectId),
                new UserProjectMapping(2, projectId));
        doReturn(mappings).when(userProjectMappingRepositoryMock).findAll();

        doReturn(user1).when(userServiceMock).getUserById(1);
        doReturn(user2).when(userServiceMock).getUserById(2);

        // When
        Set<User> users = userProjectMappingService.getUsersByProject(projectId);

        // Then
        assertEquals(2, users.size());
        assertEquals(expectedUsers, users);
    }

    /**
     * Tests the functionality to add a user to a project.
     * Verifies that the user-project mapping is correctly saved.
     */
    @Test
    public void shouldAddUserToProject() {
        // Given
        int userId = 1;

        // When
        userProjectMappingService.addUserToProject(userId, projectId);

        // Then
        verify(userProjectMappingRepositoryMock, times(1)).save(any());
    }

    /**
     * Tests the functionality to remove a user from a project.
     * Verifies that the user-project mapping is correctly deleted.
     */
    @Test
    public void shouldRemoveUserFromProject() {
        // Given
        int userId = 1;
        UserProjectMapping userProjectMapping = new UserProjectMapping(userId, projectId);
        when(userProjectMappingRepositoryMock.findByUserIdAndProjectId(userId, projectId)).thenReturn(userProjectMapping);

        // When
        userProjectMappingService.removeUserFromProject(userId, projectId);

        // Then
        verify(userProjectMappingRepositoryMock, times(1)).delete(userProjectMapping);
    }
}
