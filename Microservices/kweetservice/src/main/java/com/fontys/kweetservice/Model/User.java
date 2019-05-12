package com.fontys.kweetservice.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fontys.kweetservice.Model.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    private long id;
    @NotNull(message = "Username cannot be null")
    private String username;
    private Role role;
    @JsonIgnore
    private Profile profile;
    private String password;
    private byte[] hashedPassword;

    //TODO implement jpa and constr
    private String email;

    public long getId() {
        return id;
    }

    public User(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }

}




