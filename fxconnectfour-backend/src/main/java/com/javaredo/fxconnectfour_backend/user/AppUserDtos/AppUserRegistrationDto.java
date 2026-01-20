package com.javaredo.fxconnectfour_backend.user.AppUserDtos;

import com.javaredo.fxconnectfour_backend.auth.ValidPassword;

public class AppUserRegistrationDto {

    @ValidPassword(message = "")
    private String password;
    
    private String username;
    
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

}
