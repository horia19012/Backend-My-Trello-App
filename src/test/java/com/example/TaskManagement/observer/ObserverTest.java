package com.example.TaskManagement.observer;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;
import com.example.TaskManagement.observer.ProjectObservable;
import com.example.TaskManagement.observer.UserNotifier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ObserverTest {

    @Mock
    private User userMock;

    @Mock
    private UserNotifier userNotifier;
    private ProjectObservable projectObservable;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userNotifier = new UserNotifier(userMock);
        projectObservable = new ProjectObservable();
    }

    @Test
    public void testUserNotifierUpdate() {
        // given
        Project project = new Project("ProjectName", "Description", userMock);
        when(userMock.getUsername()).thenReturn("testUser");

        // when
        userNotifier.update(project, ProjectModification.PROJECT_DEADLINE_REACHED);

        // then
        // You can't directly assert the console output, but you can verify that the user's getUsername() method was called
        //TODO Verify case when has logic
        verify(userMock).getUsername();
    }

    @Test
    public void testProjectObservable() {
        // given
        UserNotifier userNotifierMock = mock(UserNotifier.class);
        User user1=new User("user1","fullname1","email1");
      
        Project project = new Project("ProjectName1", "Description1", user1);


        projectObservable.addObserver(userNotifierMock);

        // when
        projectObservable.updateProject(project, ProjectModification.PROJECT_DEADLINE_REACHED);

        // then
        // Verify that the update method of the userNotifierMock was called
        verify(userNotifierMock).update(project, ProjectModification.PROJECT_DEADLINE_REACHED);
    }
}
