package com.fontys.apiservice.Model.DTO;


import com.fontys.apiservice.Model.Details;
import com.fontys.apiservice.Model.Profile;
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
public class ProfileDTO extends DTO implements Serializable {
    private long id;
    private String username;
    private Details details;
    private String email;

    public static List<ProfileDTO> transform(List<Profile> profiles) {
        List<ProfileDTO> dtos = new ArrayList<>();
        for (Profile profile : profiles) {
            dtos.add(new ProfileDTO(profile.getId(), profile.getOwner().getUsername(), profile.getDetails(), profile.getOwner().getEmail()));
        }
        return dtos;
    }

    public static ProfileDTO transform(Profile profile) {
        return new ProfileDTO(profile.getId(), profile.getOwner().getUsername(), profile.getDetails(), profile.getOwner().getEmail());
    }


}
