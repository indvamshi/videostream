package com.reply.videostream.service;

import com.reply.videostream.exception.EntityExistsException;
import com.reply.videostream.model.User;
import com.reply.videostream.repository.RegistrationRepository;
import com.reply.videostream.validator.DobValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    private final Log LOGGER = LogFactory.getLog(getClass());

    private final RegistrationRepository registrationRepository;

    private final DobValidator dobValidator;

    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository, DobValidator dobValidator) {
        this.registrationRepository = registrationRepository;
        this.dobValidator = dobValidator;
    }

    @Override
    public User registerUser(User user) {
        dobValidator.validateAge(user.getDob());
        User regUser = this.registrationRepository.register(user);
        if(regUser.getId() != null) {
            return regUser;
        }
        LOGGER.error(String.format("Username %s already exists", user.getUserName()));
        throw new EntityExistsException(String.format("Username %s already exists", user.getUserName()));
    }

    @Override
    public List<User> getUsers() {
        return this.registrationRepository.getAllUsers();
    }

    @Override
    public List<User> getUserWithCC() {
        return this.registrationRepository.getAllUsersWithCCNumber();
    }

    @Override
    public List<User> getUserWithoutCC() {
        return this.registrationRepository.getAllUsersWithoutCCNumber();
    }

}
