package com.fontys.accountdoa.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fontys.accountdoa.Model.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    @NotNull(message = "Username cannot be null")
    private String username;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Profile profile;
    @Transient
    private String password;

    @Column(name = "password")
    private String hashedPassword;

    //TODO implement jpa and constr
    private String email;

    public long getId() {
        return id;
    }

    public User() {

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




