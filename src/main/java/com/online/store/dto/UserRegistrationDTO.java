package com.online.store.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String fullName;
}
