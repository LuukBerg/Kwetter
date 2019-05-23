package com.fontys.profiledoa.model.DTO;


import com.fontys.profiledoa.model.Details;
import com.fontys.profiledoa.model.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDTO extends DTO implements Serializable {
    private long id;
    private String username;
    private Details details;
    private String email;
    

    public ProfileDTO(long id, String username, Details details, String email) {
        super();
        this.id = id;
        this.username = username;
        this.details = details;
        this.email = email;
    }

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
