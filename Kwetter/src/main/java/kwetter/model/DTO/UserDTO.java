package kwetter.model.DTO;

import kwetter.JWT.Jwt;

import java.io.Serializable;

public class UserDTO implements Serializable {
    long id;
    String username;
    long profileId;
    String token;


    public UserDTO(long id, String username, long profileId, String token) {
        this.id = id;
        this.username = username;
        this.profileId = profileId;
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }
}
