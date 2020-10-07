package com.reply.videostream.service;

import com.reply.videostream.model.User;

import java.util.List;

public interface RegistrationService {

    /**
     * User registration
     * @param user details
     * @return new registered {@code User}
     */
    User registerUser(User user);

    /**
     * Retrieves all users
     * @return collection of {@code User} objects
     */
    List<User> getUsers();

    /**
     * Retrieves all users who have got Credit Card
     * @return collection of {@code User} objects
     */
    List<User> getUserWithCC();

    /**
     * Retrieves all users who have not got Credit Card
     * @return collection of {@code User} objects
     */
    List<User> getUserWithoutCC();
}
