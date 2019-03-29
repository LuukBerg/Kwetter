package kwetter.model.DTO;

import kwetter.model.models.Details;

import java.io.Serializable;

public class ProfileDTO implements Serializable {
    private long id;
    private String username;
    private Details details;
    private String email;
    public ProfileDTO() {
    }

    public ProfileDTO(long id, String username, Details details, String email) {
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
}
