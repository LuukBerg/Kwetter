package fontys.com.kwetterdoa.Model.DTO;


import fontys.com.kwetterdoa.Model.Details;
import fontys.com.kwetterdoa.Model.Profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProfileDTO extends DTO implements Serializable {
    private long id;
    private String username;
    private Details details;
    private String email;

    public ProfileDTO() {
    }

    public ProfileDTO(long id, String username, Details details, String email) {
        super();
        this.id = id;
        this.username = username;
        this.details = details;
        this.email = email;
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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<ProfileDTO> transform(List<Profile> profiles){
        List<ProfileDTO> dtos = new ArrayList<>();
        for(Profile profile : profiles){
            dtos.add(new ProfileDTO(profile.getId(),profile.getOwner().getUsername(),profile.getDetails(),profile.getOwner().getEmail()));
        }
        return dtos;
    }
    public static ProfileDTO transform(Profile profile){
        return new ProfileDTO(profile.getId(),profile.getOwner().getUsername(),profile.getDetails(),profile.getOwner().getEmail());
    }


}
