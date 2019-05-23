package com.fontys.profiledoa.model.DTO;



import com.fontys.profiledoa.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {
    long id;
    String username;
    long profileId;

    public UserDTO(long id, String username, long profileId, String token) {
        this.id = id;
        this.username = username;
        this.profileId = profileId;
    }

    public static UserDTO transform(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getProfile().getId(), null);
    }

    public static List<UserDTO> transform(List<User> users) {
        List<UserDTO> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(new UserDTO(user.getId(), user.getUsername(), user.getProfile().getId(), null));
        }
        return dtos;

    }
}
