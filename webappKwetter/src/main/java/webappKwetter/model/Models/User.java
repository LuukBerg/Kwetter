package webappKwetter.model.Models;




import webappKwetter.model.Enums.Role;


import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    private Role role;
    @OneToOne
    private Profile profile;

    public long getId() {
        return id;
    }

    public User(){

    }
    public User(String username, Role role) {
        this.username = username;
        this.role = role;
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
}


