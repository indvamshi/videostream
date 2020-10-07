package com.reply.videostream.repository;

import com.reply.videostream.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("CRUD Operations on User Registration")
public class RegistrationRepositoryTest {

    @Test
    @DisplayName("register user")
    public void registerUser() {
        User user = new User("User1", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");

        RegistrationRepository repo = new RegistrationRepository();
        repo.register(user);

        User dbUser = repo.getAllUsers().get(0);

        Assertions.assertAll(
                () -> assertTrue(dbUser.getId() != null),
                () -> assertEquals("User1", dbUser.getUserName()),
                () -> assertEquals("passwoR1", dbUser.getPassword()),
                () -> assertEquals("test@gmail.com", dbUser.getEmail()),
                () -> assertEquals("1990-07-16T19:23:51", dbUser.getDob())
        );
    }

    @Test
    @DisplayName("duplicate user")
    public void duplicateUser() {
        User user = new User("User1", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");

        RegistrationRepository repo = new RegistrationRepository();
        repo.register(user);

        user = new User("User1", "passwoR1", "test@gmail.com",
                        "1990-07-16T19:23:51");

        User newUser = repo.register(user);
        Assertions.assertTrue(newUser.getId() == null);
    }

    @Test
    @DisplayName("list all users with credit card number")
    public void listAllUsersWithCreditCardNumber() {
        User user = new User("User1", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");

        RegistrationRepository repo = new RegistrationRepository();
        repo.register(user);

        user = new User("User2", "passwoR2", "test2@gmail.com",
                        "1989-07-16T19:23:51", "123456781012131");

        repo.register(user);

        Assertions.assertEquals(repo.getAllUsersWithCCNumber().size(), 1);

        User dbUser = repo.getAllUsersWithCCNumber().get(0);

        Assertions.assertAll(
                () -> assertTrue(dbUser.getId() != null),
                () -> assertEquals("User2", dbUser.getUserName()),
                () -> assertEquals("passwoR2", dbUser.getPassword()),
                () -> assertEquals("test2@gmail.com", dbUser.getEmail()),
                () -> assertEquals("1989-07-16T19:23:51", dbUser.getDob()),
                () -> assertEquals("123456781012131", dbUser.getCcNumber())
        );
    }

    @Test
    @DisplayName("list all users without credit card number")
    public void listAllUsersWithoutCreditCardNumber() {
        User user = new User("User1", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");

        RegistrationRepository repo = new RegistrationRepository();
        repo.register(user);

        user = new User("User2", "passwoR2", "test2@gmail.com",
                        "1989-07-16T19:23:51", "123456781012131");

        repo.register(user);

        List<User> allUsersWithoutCCNumber = repo.getAllUsersWithoutCCNumber();
        Assertions.assertEquals(allUsersWithoutCCNumber.size(), 1);

        User dbUser = allUsersWithoutCCNumber.get(0);

        Assertions.assertAll(
                () -> assertTrue(dbUser.getId() != null),
                () -> assertEquals("User1", dbUser.getUserName()),
                () -> assertEquals("passwoR1", dbUser.getPassword()),
                () -> assertEquals("test@gmail.com", dbUser.getEmail()),
                () -> assertEquals("1990-07-16T19:23:51", dbUser.getDob())
        );
    }
}
