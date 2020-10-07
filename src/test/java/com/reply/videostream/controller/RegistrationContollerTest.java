package com.reply.videostream.controller;

import com.reply.videostream.model.User;
import com.reply.videostream.service.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@DisplayName("CRUD operations on User")
public class RegistrationContollerTest {

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private RegistrationService registrationService;

    @BeforeAll
    public static void init() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void registerUser() {
        registrationController = new RegistrationController(registrationService);

        User user = new User("User", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");
        User newUser = user;
        newUser.setId(1L);
        Mockito.when(registrationService.registerUser(user)).thenReturn(newUser);

        ResponseEntity<User> regUser = registrationController.register(user);

        Assertions.assertEquals(201, regUser.getStatusCodeValue());
        Assertions.assertAll(
                () -> assertTrue(newUser.getId() != null),
                () -> assertEquals("User", newUser.getUserName()),
                () -> assertEquals("passwoR1", newUser.getPassword()),
                () -> assertEquals("test@gmail.com", newUser.getEmail()),
                () -> assertEquals("1990-07-16T19:23:51", newUser.getDob())
        );
    }

    @Test
    void getAllUsers() {
        registrationController = new RegistrationController(registrationService);

        User user = new User("User", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");
        user.setId(1L);
        List<User> userList = new ArrayList<>();
        userList.add(user);


        Mockito.when(registrationService.getUsers()).thenReturn(userList);

        ResponseEntity<List<User>> users = registrationController.getUsers("");

        Assertions.assertTrue(!users.getBody().isEmpty());
        Assertions.assertEquals(200, users.getStatusCodeValue());
    }

    @Test
    void getAllUsersWithCCNumber() {
        registrationController = new RegistrationController(registrationService);

        List<User> userList = new ArrayList<>();

        Mockito.when(registrationService.getUserWithCC()).thenReturn(userList);

        ResponseEntity<List<User>> users = registrationController.getUsers("Yes");
        Assertions.assertTrue(users.getBody().isEmpty());
        Assertions.assertEquals(200, users.getStatusCodeValue());
    }

    @Test
    void getAllUsersWithoutCCNumber(){
        registrationController = new RegistrationController(registrationService);

        User user = new User("User", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");
        user.setId(1L);
        List<User> userList = new ArrayList<>();
        userList.add(user);


        Mockito.when(registrationService.getUserWithoutCC()).thenReturn(userList);

        ResponseEntity<List<User>> users = registrationController.getUsers("No");
        Assertions.assertTrue(!users.getBody().isEmpty());
        Assertions.assertEquals(200, users.getStatusCodeValue());
    }
}
