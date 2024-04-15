package com.example.TaskManagement.test;

import com.example.TaskManagement.entity.User;
import com.example.TaskManagement.repository.UsersRepository;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

public class ServiceTest {
    @Mock
    private Date dateMoc;
    private UsersRepository usersRepository;
    private User user;

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        //this.dateMoc = new Date();
    }
}
