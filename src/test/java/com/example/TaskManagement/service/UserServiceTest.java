package com.example.TaskManagement.service;

import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.repository.UsersRepository;
import com.example.TaskManagement.service.impl.DefaultUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
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
    public void shouldGetUserById() {
        // given
        doReturn(Optional.of(user)).when(userRepoMock).findById(user.getId());

        // when
        User actualUser = userService.getUserById(user.getId());

        // then
        assertEquals(user, actualUser);
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

    @Test
    public void shouldReturnAllUsers() {
        // given
        List<User> userList = Arrays.asList(new User("user1", "User One", "user1@example.com"),
                new User("user2", "User Two", "user2@example.com"));
        doReturn(userList).when(userRepoMock).findAll();

        // when
        List<User> allUsers = userService.getAllUsers();

        // then
        assertEquals(userList.size(), allUsers.size());
        assertEquals(userList.get(0), allUsers.get(0));
        assertEquals(userList.get(1), allUsers.get(1));
    }

    @Test
    public void shouldSaveUser_whenUserNotFoundAndUpdated() {
        // given
        User newUser = new User("newUser", "New User", "newuser@example.com");
        newUser.setId(ID);
        doReturn(Optional.empty()).when(userRepoMock).findById(ID);

        // when
        userService.updateUser(newUser);

        // then
        verify(userRepoMock, never()).save(newUser);
    }


}
