package com.reply.videostream.repository;

import com.reply.videostream.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


/**
 * Data access layer to register {@code User} details
 *
 */
@Repository("registrationRepository")
public class RegistrationRepository {

    private final Log LOGGER = LogFactory.getLog(getClass());

    private final Map<Long, User> userMap = new ConcurrentHashMap<>();

    private final AtomicLong atomicLong = new AtomicLong();

    public User register(User user) {
        LOGGER.debug("Registering user into DB");
        boolean userExists = getAllUsers().stream().anyMatch(userStream -> userStream.getUserName().equals(user.getUserName()));
        if(!userExists) {
            user.setId(atomicLong.incrementAndGet());
            userMap.put(user.getId(), user);
        }
        return user;
    }

    public List<User> getAllUsersWithCCNumber() {
        LOGGER.debug("Retrieving all users from db filtered by credit card number");
        return getAllUsers()
                .stream()
                .filter(user -> user.getCcNumber() != null)
                .collect(Collectors.toList());
    }

    public List<User> getAllUsersWithoutCCNumber() {
        LOGGER.debug("Retrieving all users from db filtered by without credit card number");
        return getAllUsers()
                .stream()
                .filter(user -> StringUtils.isEmpty(user.getCcNumber()))
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        LOGGER.debug("Retrieving all users from db with/without credit card number");
        return new ArrayList<>(userMap.values());
    }
}
