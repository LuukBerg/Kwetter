package com.fontys.accountservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Profile implements Serializable {
    private long id;
    private User owner;
    @JsonIgnore
    private List<Profile> following;
    @JsonIgnore
    private List<Profile> followers;
    private Details details;
    @JsonIgnore
    private List<Kweet> kweets;


    public Profile(User owner, Details details) {
        this.owner = owner;
        this.following = new LinkedList<>();
        this.followers = new LinkedList<>();
        this.details = details;
        this.kweets = new LinkedList<>();
        owner.setProfile(this);
    }

    public void addFollower(Profile profile) {
        followers.add(profile);
    }

    public void addKweet(Kweet kweet) {
        kweets.add(kweet);
    }

    public void addFollowing(Profile followingProfile) {
        following.add(followingProfile);
    }

    public void removeFollowing(Profile followingProfile) {
        //TODO try catch?
        following.remove(followingProfile);
    }

    public void removeFollower(Profile profile) {
        //TODO try catch?
        followers.remove(profile);
    }


}

