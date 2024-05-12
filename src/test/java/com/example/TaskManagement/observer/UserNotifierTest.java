package com.example.TaskManagement.observer;

import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.ProjectModification;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for UserNotifier.
 * This class tests the functionality of the UserNotifier within the observer pattern implementation,
 * ensuring that user notifications are handled correctly when project modifications occur.
 */
public class UserNotifierTest {

    @Mock
    private User userMock;

    @Mock
    private UserNotifier userNotifier;
    private ProjectObservable projectObservable;

    /**
     * Sets up the testing environment before each test.
     * This method initializes Mockito annotations, and configures the mocks and project observable used in the tests.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userNotifier = new UserNotifier(userMock);
        projectObservable = new ProjectObservable();
    }

    /**
     * Tests the update method of UserNotifier to ensure it correctly handles the notification process.
     * The test verifies that the UserNotifier correctly calls the getUsername method on the User object,
     * which is part of the notification logic when a project's deadline is reached.
     */
    @Test
    public void testUserNotifierUpdate() {
        // Given
        Project project = new Project("ProjectName", "Description", userMock);
        when(userMock.getUsername()).thenReturn("testUser");

        // When
        userNotifier.update(project, ProjectModification.PROJECT_DEADLINE_REACHED);

        // Then
        // Verify that the getUsername method is called, demonstrating that UserNotifier queries the user's information.
        verify(userMock).getUsername();
    }
}