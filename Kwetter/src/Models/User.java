package Models;

import Enums.Role;

@Entity
public class User {
    private long id;
    private String username;
    private Role role;
    private Profile profile;

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
}


