package com.online.store.controller;

import com.online.store.dto.StoreUserDTO;
import com.online.store.dto.UserCredentialsDTO;
import com.online.store.dto.UserRegistrationDTO;
import com.online.store.exception.ApiException;
import com.online.store.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(path = "/login")
    public StoreUserDTO login(@RequestBody UserCredentialsDTO userCredentials) {
        try {
            StoreUserDTO userDTO = authenticationService.login(userCredentials);
            Authentication auth = new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword());
            SecurityContextHolder.getContext().setAuthentication(auth);
            return userDTO;
        } catch (Exception ex) {
            throw new ApiException("Login was unsuccessful!");
        }
    }

    @PostMapping(path = "/register")
    public StoreUserDTO register(@RequestBody UserRegistrationDTO userRegistration) {
        try {
            return authenticationService.register(userRegistration);
        } catch (Exception ex) {
            throw new ApiException("Could not perform new user registration!");
        }
    }

}
