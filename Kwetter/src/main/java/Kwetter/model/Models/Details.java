package Kwetter.model.Models;

import javax.persistence.Embeddable;

@Embeddable
public class Details {
    private String name;
    private String location;
    private String web;
    private String bio;

    public Details() {
    }

    public Details(String name, String location, String web, String bio) {
        this.name = name;
        this.location = location;
        this.web = web;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
