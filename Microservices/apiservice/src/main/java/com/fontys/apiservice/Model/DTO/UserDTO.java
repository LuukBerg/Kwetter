package com.fontys.apiservice.Model.DTO;


import com.fontys.apiservice.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
    private long id;
    private String username;
    private long profileId;
    private String token;



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
