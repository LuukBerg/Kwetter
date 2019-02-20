package Kwetter.model.Models;




import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Profile {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private User owner;
    @ManyToMany
    private List<Profile> following;
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "following")
    private List <Profile> followers;
    @Embedded
    private Details details;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "owner")
    private List<Kweet> kweets;

    public Profile() {
    }


    public Profile(User owner,Details details) {
        this.owner = owner;
        this.following = new LinkedList<>();
        this.followers = new LinkedList<>();
        this.details = details;
        this.kweets = new LinkedList<>();
    }

    public void addFollower(Profile profile){
        followers.add(profile);
    }


    public List<Profile> getFollowing() {
        return following;
    }


    public List<Profile> getFollowers() {
        return followers;
    }
    public Kweet postKweet(String content){
        Kweet kweet = null;
        if(content != null){
            kweet = new Kweet(content, this);
            kweets.add(kweet);

        }
        return kweet;

    }

    public Details getDetails() {
        return details;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }
    public List<Kweet> getTimeline(){
        List<Kweet> timeline = null;
        if(kweets != null) {
            timeline = new LinkedList<Kweet>();

            for (int i = kweets.size() - 1; i >= kweets.size() - 10; i--) {
                if (kweets.get(i) != null) timeline.add(kweets.get(i));
            }
        }
        return timeline;

    }

    public long getId() {
        return id;
    }

    public void addKweet(Kweet kweet) {
        kweets.add(kweet);
    }

    public void setId(long id) {
        this.id =id;
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
