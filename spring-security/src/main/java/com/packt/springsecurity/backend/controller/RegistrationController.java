package com.packt.springsecurity.backend.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.Sets;
import com.packt.springsecurity.backend.dto.RegistrationDto;
import com.packt.springsecurity.backend.persistence.model.Authority;
import com.packt.springsecurity.backend.persistence.model.User;
import com.packt.springsecurity.backend.persistence.service.IAuthorityService;
import com.packt.springsecurity.backend.persistence.service.IUserService;
import com.packt.springsecurity.backend.persistence.setup.SecurityConstants;

@Controller
public class RegistrationController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IAuthorityService authorityService;

    public RegistrationController() {
        super();
    }

    // API

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerUser(@RequestBody final RegistrationDto registration) {
        final Set<Authority> userAuthorities = retrieveUserAuthorities();
        userService.create(new User(registration.getUsername(), registration.getPassword(), userAuthorities));
    }

    // util

    private final Set<Authority> retrieveUserAuthorities() {
        final Set<Authority> userAuthorities = Sets.newHashSet();
        final Set<String> userAuthorityNames = SecurityConstants.getUserAuthorities();
        for (String authorityName : userAuthorityNames) {
            Authority authByName = authorityService.findByName(authorityName);
            if (authByName != null) {
                userAuthorities.add(authByName);
            }
        }
        return userAuthorities;
    }

}
