package com.example.TaskManagement.service;

import com.example.TaskManagement.DTO.UserDTO;
import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.enums.Role;
import com.example.TaskManagement.mapper.UserMapper;
import com.example.TaskManagement.repository.UsersRepository;
import com.example.TaskManagement.service.impl.DefaultUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String FULL_NAME = "fullname";
    private static final String PASSWORD="123";
    private static final int ID = 1;
    private Role ROLE= Role.USER;

    @Mock
    private UsersRepository userRepoMock;

    @Mock
    private PasswordEncoder passwordEncoder;
    private User user;
    private UserDTO userDTO;

    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //this.dateMoc = new Date();
        user = new User(USERNAME, FULL_NAME, EMAIL,PASSWORD);
        user.setId(ID);

        userDTO=new UserDTO(USERNAME,FULL_NAME,EMAIL,PASSWORD,ROLE);

        userService = new DefaultUserService(userRepoMock,passwordEncoder);
    }

    @Test
    public void shouldSaveUser() {
        //when
        userService.saveUser(userDTO);

        //then
        verify(userRepoMock).save(UserMapper.toEntity(userDTO));
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
        doReturn(Optional.of(user)).when(userRepoMock).findByUsername(user.getUsername());

        UserDTO foundUserDTO=UserMapper.toDTO(user);
        foundUserDTO.setId(user.getId());
        //when
        userService.updateUser(foundUserDTO);

        //then
        verify(userRepoMock).save(user);
    }

    @Test
    public void shouldNotSaveUser_whenUserNotFound() {
        //given
        doReturn(Optional.empty()).when(userRepoMock).findById(ID);

        //when
        userService.updateUser(userDTO);

        //then
        verify(userRepoMock, never()).save(UserMapper.toEntity(userDTO));
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
        UserDTO newUser = new UserDTO("newUser", "New User", "newuser@example.com",PASSWORD,ROLE);
        newUser.setId(ID);
        doReturn(Optional.empty()).when(userRepoMock).findById(ID);

        // when
        userService.updateUser(newUser);

        // then
        verify(userRepoMock, never()).save(UserMapper.toEntity(newUser));
    }
//    @Test
//    public void testLoadUserByUsername_UserExists() {
//        // Arrange
//        User mockUser = new User("john_doe", "John Doe", "john@example.com", "encoded_password");
//        when(userRepoMock.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
//
//        // Act
//        UserDetails userDetails = userService.loadUserByUsername("john_doe");
//
//        // Assert
//        assertNotNull(userDetails);
//        assertEquals("john_doe", userDetails.getUsername());
//        assertEquals("encoded_password", userDetails.getPassword());
//    }



}
