package com.packt.springsecurity.backend.dto;

public class RegistrationDto {

    private String username;
    private String password;

    public RegistrationDto() {
        super();
    }

    // API

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}
