package com.example.TaskManagement.server;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.entity.UserProjectMapping;
import com.example.TaskManagement.repository.ProjectsRepository;
import com.example.TaskManagement.repository.UserProjectMappingRepository;
import com.example.TaskManagement.service.UserService;
import com.example.TaskManagement.service.impl.DefaultUserProjectMappingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserProjectMappingServiceTest {

    @Mock
    private UserProjectMappingRepository userProjectMappingRepositoryMock;

    @Mock
    private ProjectsRepository projectsRepositoryMock;

    @Mock
    private UserService userServiceMock;

    private DefaultUserProjectMappingService userProjectMappingService;

    private int projectId=1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userProjectMappingService = new DefaultUserProjectMappingService(userProjectMappingRepositoryMock, projectsRepositoryMock, userServiceMock);
    }

    @Test
    public void shouldGetProjectsByUser() {
        // Given
        int userId = 1;
        List<UserProjectMapping> mappings = new ArrayList<>();
        mappings.add(new UserProjectMapping(userId, 1));
        mappings.add(new UserProjectMapping(userId, 2));
        when(userProjectMappingRepositoryMock.findAll()).thenReturn(mappings);

        Project project1 = new Project("Project 1", "Description 1");
        Project project2 = new Project("Project 2", "Description 2");
        when(projectsRepositoryMock.findById(1)).thenReturn(java.util.Optional.of(project1));
        when(projectsRepositoryMock.findById(2)).thenReturn(java.util.Optional.of(project2));

        // When
        List<Project> projects = userProjectMappingService.getProjectsByUser(userId);

        // Then
        assertEquals(2, projects.size());
    }

    @Test
    public void shouldGetUsersByProject_whenProjectIdExisting() {
        // Given

        User user1=new User("user1","fullname1","email1");
        user1.setId(1);
        User user2=new User("user2","fullname2","email2");
        user2.setId(2);

        Set<User> expectedUsers=new HashSet<>();
        expectedUsers.add(user1);
        expectedUsers.add(user2);



        List<UserProjectMapping>mappings =new ArrayList<>();
        UserProjectMapping mapping1=new UserProjectMapping(1,projectId);
        UserProjectMapping mapping2=new UserProjectMapping(2,projectId);
        mappings.add(mapping1);
        mappings.add(mapping2);
        doReturn(mappings).when(userProjectMappingRepositoryMock).findAll();

        doReturn(user1).when(userServiceMock).getUserById(1);
        doReturn(user2).when(userServiceMock).getUserById(2);

        //When
        Set<User> users = userProjectMappingService.getUsersByProject(projectId);

        //Then
        assertEquals(2,users.size());
        assertEquals(users,expectedUsers);
    }

    @Test
    public void shouldAddUserToProject() {
        // Given
        int userId = 1;

        // When
        userProjectMappingService.addUserToProject(userId, projectId);

        // Then
        verify(userProjectMappingRepositoryMock, times(1)).save(any());
    }


    @Test
    public void shouldRemoveUserFromProject() {
        // Given
        int userId = 1;
        int projectId = 1;
        UserProjectMapping userProjectMapping = new UserProjectMapping(userId, projectId);
        when(userProjectMappingRepositoryMock.findByUserIdAndProjectId(userId, projectId)).thenReturn(userProjectMapping);

        // When
        userProjectMappingService.removeUserFromProject(userId, projectId);

        // Then
        verify(userProjectMappingRepositoryMock, times(1)).delete(userProjectMapping);
    }
}
