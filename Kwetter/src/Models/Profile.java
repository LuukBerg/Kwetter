package Models;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Profile {
    private Long id;
    private User owner;
    private List<Profile> following;
    private List <Profile> followers;
    private Details details;
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

    public void followProfile(Profile profile){
        following.add(profile);
        profile.addFollower(this);

    }
    private void addFollower(Profile profile){
        followers.add(profile);
    }


    public List<Profile> getFollowing() {
        return following;
    }


    public List<Profile> getFollowers() {
        return followers;
    }
    public void postKweet(Kweet kweet){
        if(kweets != null){
            kweets.add(kweet);
        }

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

}
