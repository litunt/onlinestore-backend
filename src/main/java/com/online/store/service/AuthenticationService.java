package com.online.store.service;

import com.online.store.dto.StoreUserDTO;
import com.online.store.dto.UserCredentialsDTO;
import com.online.store.dto.UserRegistrationDTO;
import com.online.store.model.StoreUser;
import com.online.store.model.UserCredentials;
import com.online.store.repository.StoreUserRepository;
import com.online.store.repository.UserCredentialsRepository;
import com.online.store.transformer.StoreUserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    @Autowired
    private UserCredentialsRepository credentialsRepository;

    @Autowired
    private StoreUserRepository storeUserRepository;

    @Autowired
    private StoreUserTransformer storeUserTransformer;

    public StoreUserDTO login(UserCredentialsDTO credentials) {
        Long userId = credentialsRepository.findUserIdByUsernameAndPassword(credentials.getUsername(), credentials.getPassword());
        if (userId != null) {
            StoreUserDTO userDTO = storeUserTransformer.entityToDto(storeUserRepository.getById(userId));
            userDTO.setUsername(credentials.getUsername());
            return userDTO;
        }
        return null;
    }

    public String register(UserRegistrationDTO registration) {
        StoreUser storeUser = new StoreUser();
        storeUser.setName(registration.getFullName());
        storeUser.setRegDate(LocalDateTime.now());
        storeUser.setIsActive(true);
        storeUser = storeUserRepository.save(storeUser);

        UserCredentials credentials = new UserCredentials();
        credentials.setPassword(registration.getPassword());
        credentials.setUsername(registration.getUsername());
        credentials.setUser(storeUser);
        credentialsRepository.save(credentials);
        return "Success";
    }
}
