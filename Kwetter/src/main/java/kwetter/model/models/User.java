package kwetter.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kwetter.model.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    @NotNull(message = "Username cannot be null")
    private String username;
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Profile profile;

    private String password;

    //TODO implement jpa and constr
    //@NotNull(message = "Email should be valid")
    private String email;

    public long getId() {
        return id;
    }

    public User(){

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setId(long id) {
        this.id = id;
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
}



