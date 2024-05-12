package com.example.TaskManagement.observer;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test class for ProjectObservable.
 * This class verifies the functionality of the ProjectObservable, particularly focusing on its ability to notify observers about project changes.
 */
public class ProjectObservableTest {

    @Mock
    private User userMock;

    @Mock
    private UserNotifier userNotifier;
    private ProjectObservable projectObservable;

    /**
     * Sets up the testing environment before each test.
     * Initializes Mockito annotations, configures user notifier and project observable objects for use in the tests.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userNotifier = new UserNotifier(userMock);
        projectObservable = new ProjectObservable();
    }

    /**
     * Tests the ability of ProjectObservable to notify its observers when a project is updated.
     * Specifically, this test checks whether the update method of an observer (UserNotifier) is called with the correct parameters when a project deadline is reached.
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
