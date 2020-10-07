package com.reply.videostream.controller;

import com.reply.videostream.model.User;
import com.reply.videostream.service.RegistrationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


/**
 * RESTful api for registering {@code User}
 *
 */
@RestController
@RequestMapping("api")
public class RegistrationController {

    private final Log LOGGER = LogFactory.getLog(getClass());

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody @Valid User user) {
        LOGGER.debug("registering user");
        User newUser = this.registrationService.registerUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String creditCard){
        LOGGER.debug("Retrieving all users");
        if("Yes".equalsIgnoreCase(creditCard)) {
            return ResponseEntity.ok(registrationService.getUserWithCC());
        } else if ("No".equalsIgnoreCase(creditCard)){
            return ResponseEntity.ok(registrationService.getUserWithoutCC());
        } else {
            return ResponseEntity.ok(registrationService.getUsers());
        }
    }

}
