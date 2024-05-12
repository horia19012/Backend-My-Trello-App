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

/**
 * This class contains tests for observer pattern implementation within the project management system.
 * It tests the functionality of notifying users about changes to projects and how observers respond to notifications.
 */
public class ObserverTest {

    @Mock
    private User userMock;

    @Mock
    private UserNotifier userNotifier;
    private ProjectObservable projectObservable;

    /**
     * Initializes mocks and sets up the test environment before each test.
     * It creates an instance of UserNotifier and ProjectObservable to be used in the tests.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userNotifier = new UserNotifier(userMock);
        projectObservable = new ProjectObservable();
    }

    /**
     * Tests the update mechanism of UserNotifier when a project reaches its deadline.
     * It verifies that the notifier correctly utilizes the User object during the notification process.
     */
    @Test
    public void testUserNotifierUpdate() {
        // given
        Project project = new Project("ProjectName", "Description", userMock);
        when(userMock.getUsername()).thenReturn("testUser");

        // when
        userNotifier.update(project, ProjectModification.PROJECT_DEADLINE_REACHED);

        // then
        // The test checks that the getUsername method is called, implying that the UserNotifier queried the user's information.
        verify(userMock).getUsername();
    }

    /**
     * Tests the notification mechanism within ProjectObservable to ensure that it correctly notifies all registered observers
     * about a specific project modification, in this case, when a project deadline is reached.
     */
    @Test
    public void testProjectObservable() {
        // given
        UserNotifier userNotifierMock = mock(UserNotifier.class);
        User user1 = new User("user1", "fullname1", "email1");
        Project project = new Project("ProjectName1", "Description1", user1);

        projectObservable.addObserver(userNotifierMock);

        // when
        projectObservable.updateProject(project, ProjectModification.PROJECT_DEADLINE_REACHED);

        // then
        // Verify that the update method of the userNotifierMock was called, confirming that notifications are being dispatched correctly.
        verify(userNotifierMock).update(project, ProjectModification.PROJECT_DEADLINE_REACHED);
    }
}
