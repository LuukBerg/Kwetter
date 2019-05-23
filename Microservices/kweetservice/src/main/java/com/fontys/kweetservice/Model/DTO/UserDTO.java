package com.fontys.kweetservice.Model.DTO;



import com.fontys.kweetservice.Model.User;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO implements Serializable {
    private long id;
    private String username;
    private long profileId;

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
