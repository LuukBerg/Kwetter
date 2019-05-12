package com.fontys.accountdoa.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Profile implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User owner;

    @ManyToMany(mappedBy = "followers", cascade = CascadeType.PERSIST)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Profile> following;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "following_followers", joinColumns = @JoinColumn(name = "following_id"), inverseJoinColumns = @JoinColumn(name = "followers_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Profile> followers;

    @Embedded
    private Details details;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonIgnore
    private List<Kweet> kweets;


    public Profile() {
    }


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

    @Transactional
    public List<Profile> getFollowing() {
        return following;
    }

    @Transactional
    public List<Profile> getFollowers() {
        return followers;
    }


    public Details getDetails() {
        return details;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public long getId() {
        return id;
    }

    public void addKweet(Kweet kweet) {
        kweets.add(kweet);
    }

    public void setId(long id) {
        this.id = id;
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

    public User getOwner() {
        return owner;
    }

    public void setFollowing(List<Profile> following) {
        this.following = following;
    }

    public void setFollowers(List<Profile> followers) {
        this.followers = followers;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

}

