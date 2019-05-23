package com.fontys.profiledoa.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
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

