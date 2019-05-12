package com.fontys.accountservice.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterDTO implements Serializable {

    private String username;
    private String email;
    private String password;

    private String name;
    private String location;
    private String web;
    private String bio;
    private String hashed;

    public RegisterDTO() {
    }

    public RegisterDTO(String username, String email, String password, String name, String location, String web, String bio) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.location = location;
        this.web = web;
        this.bio = bio;
    }


}
