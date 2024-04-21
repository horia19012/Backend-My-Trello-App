package com.example.TaskManagement.server;

import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.repository.UsersRepository;
import com.example.TaskManagement.service.UserService;
import com.example.TaskManagement.service.impl.DefaultUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String FULL_NAME = "fullname";
    private static final int ID = 1;

    @Mock
    private UsersRepository userRepoMock;
    private User user;

    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //this.dateMoc = new Date();
        user = new User(USERNAME, FULL_NAME, EMAIL);
        user.setId(ID);
        userService = new DefaultUserService(userRepoMock);
    }

    @Test
    public void shouldSaveUser() {
        //when
        userService.saveUser(user);

        //then
        verify(userRepoMock).save(user);
    }

    @Test
    public void shouldReturnUser_whenUserFoundById() {
        //given
        Optional<User> optionalUser = Optional.of(user);
        doReturn(optionalUser).when(userRepoMock).findById(ID);

        //when
        User actualUser = userService.getUserById(ID);


        //then
        assertEquals(user, actualUser);
    }

    @Test
    public void shouldReturnNull_whenUserNotFoundById() {
        //given
        doReturn(Optional.empty()).when(userRepoMock).findById(ID);

        //when
        User actualUser = userService.getUserById(ID);


        //then
        assertNull(actualUser);
    }

    @Test
    public void shouldSaveUser_whenUserFound() {
        //given
        doReturn(Optional.of(user)).when(userRepoMock).findById(ID);

        //when
        userService.updateUser(user);

        //then
        verify(userRepoMock).save(user);
    }

    @Test
    public void shouldNotSaveUser_whenUserNotFound() {
        //given
        doReturn(Optional.empty()).when(userRepoMock).findById(ID);

        //when
        userService.updateUser(user);

        //then
        verify(userRepoMock, never()).save(user);
    }

    @Test
    public void shouldDeleteUserById() {
        //when
        userService.deleteUser(ID);

        //then
        verify(userRepoMock).deleteById(ID);
    }

}
