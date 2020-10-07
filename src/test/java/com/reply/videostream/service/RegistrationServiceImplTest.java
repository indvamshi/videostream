package com.reply.videostream.service;

import com.reply.videostream.exception.EntityExistsException;
import com.reply.videostream.model.User;
import com.reply.videostream.repository.RegistrationRepository;
import com.reply.videostream.validator.DobValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Registration Service")
public class RegistrationServiceImplTest {

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private DobValidator dobValidator;

    @BeforeEach
    public void init() {
        registrationService = new RegistrationServiceImpl(registrationRepository, dobValidator);
    }

    @Test
    @DisplayName("register user")
    public void registerUser() {
        User user = new User("User2", "passwoR2", "test2@gmail.com",
                        "1989-07-16T19:23:51", "123456781012131");

        User newUser = user;
        newUser.setId(1L);

        Mockito.doNothing().when(dobValidator).validateAge("1989-07-16T19:23:51");
        Mockito.when(registrationRepository.register(user)).thenReturn(newUser);

        Assertions.assertEquals(registrationService.registerUser(user), newUser);
    }

    @Test
    @DisplayName("register with duplicate user name")
    public void registerUserWithDuplicateName() {
        User user = new User("User2", "passwoR2", "test2@gmail.com",
                             "1989-07-16T19:23:51", "123456781012131");

        Mockito.doNothing().when(dobValidator).validateAge("1989-07-16T19:23:51");
        Mockito.when(registrationRepository.register(user)).thenReturn(user);

        Assertions.assertThrows(EntityExistsException.class, () -> registrationService.registerUser(user));
    }

    @Test
    @DisplayName("retrieve all users")
    public void retrieveAllUsers() {
        User user = new User("User2", "passwoR2", "test2@gmail.com",
                             "1989-07-16T19:23:51", "123456781012131");

        List<User> userList = new ArrayList<>();
        userList.add(user);

        Mockito.when(registrationRepository.getAllUsers()).thenReturn(userList);

        Assertions.assertEquals(registrationService.getUsers().size() , 1);
    }

    @Test
    @DisplayName("retrieve all users with CC Number")
    public void retrieveAllUsersWithCCNumber() {
        User user = new User("User2", "passwoR2", "test2@gmail.com",
                             "1989-07-16T19:23:51", "123456781012131");

        List<User> userList = new ArrayList<>();
        userList.add(user);

        Mockito.when(registrationRepository.getAllUsersWithCCNumber()).thenReturn(userList);

        Assertions.assertEquals(registrationService.getUserWithCC().size() , 1);
    }

    @Test
    @DisplayName("retrieve all users with out CC Number")
    public void retrieveAllUsersWithoutCCNumber() {
        List<User> userList = new ArrayList<>();

        Mockito.when(registrationRepository.getAllUsersWithoutCCNumber()).thenReturn(userList);

        Assertions.assertEquals(registrationService.getUserWithoutCC().size() , 0);
    }
}
