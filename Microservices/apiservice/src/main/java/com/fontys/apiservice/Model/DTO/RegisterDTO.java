package com.fontys.apiservice.Model.DTO;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class RegisterDTO implements Serializable {

    private String username;
    private String email;
    private String password;

    private String name;
    private String location;
    private String web;
    private String bio;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
