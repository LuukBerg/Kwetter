package kwetter.model.DTO;

import kwetter.JWT.Jwt;
import kwetter.model.models.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public static UserDTO transform(User user){
        return new UserDTO(user.getId(),user.getUsername(), user.getProfile().getId(), null);
    }
    public static List<UserDTO> transform(List<User> users){
        List<UserDTO> dtos = new ArrayList<>();
        for (User user : users){
            dtos.add(new UserDTO(user.getId(),user.getUsername(), user.getProfile().getId(), null));
        }
        return dtos;

    }
}
