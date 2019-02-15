package webappKwetter.test.ModelTests;



import org.junit.Assert;
import webappKwetter.model.Commands.Hearth;
import webappKwetter.model.Commands.React;
import webappKwetter.model.Enums.Role;
import webappKwetter.model.Models.Details;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;
import webappKwetter.model.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;


import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


class ProfileTest {

    private List<Profile> profileList;
    //(User owner, Image image, List<Profile> following, List<Profile> followers, Details details, List<Kweet> kweets)
    // String name, String location, String web, String bio
    @BeforeEach
    public  void beforeEach() throws IOException {
        profileList = new LinkedList<>();

        for (int i = 0; i < 10 ; i++){
            Profile profile = new Profile(new User("test" + i , Role.user),  new Details("testName" + i, "testLocation" + i,"testWeb" + i, "testBio" + i));
            profileList.add(profile);
        }
        for (int i = 0; i < 10 ; i++){
            for (int j = 0; j < 10 ; j++){
                    profileList.get(i).postKweet("profile " + i + " kweet " + j);
            }
        }

    }


    @Test
    void followProfile() {
        Profile profile = profileList.get(0);
        Profile followingProfile = profileList.get(1);
        Assert.assertEquals(profile.getFollowers().size(), 0);
        Assert.assertEquals(followingProfile.getFollowing().size(), 0);
        followingProfile.followProfile(profile);
        Assert.assertEquals(profile.getFollowers().get(0), followingProfile);
        Assert.assertEquals(followingProfile.getFollowing().get(0), profile);
    }

    @Test
    void getTimeLine(){
        Profile testProfile = profileList.get(0);
        List<Kweet> timeline = testProfile.getTimeline();
        Assert.assertEquals(timeline.size(), 10);
        Assert.assertEquals(timeline.get(0).getContent(), "profile 0 kweet 9");
        testProfile.postKweet("profile 0 kweet 10");
        timeline = testProfile.getTimeline();
        Assert.assertEquals(timeline.size(), 10);
        Assert.assertEquals(timeline.get(0).getContent(), "profile 0 kweet 10");
    }

    @Test
    void postKweet() {
        String content = "test";
        Profile testProfile = profileList.get(0);
        Assert.assertEquals(testProfile.getKweets().size(), 10);
        Date date = new Date();
        Kweet testKweet = testProfile.postKweet(content);
        Assert.assertEquals(date, testKweet.getDate());
        date.setTime(10);
        Assert.assertNotEquals(date, testKweet.getDate());
        Assert.assertEquals(testProfile.getKweets().get(10), testKweet);
    }
    @Test
    void detailsTest(){
        Profile testProfile = profileList.get(0);
        Assert.assertEquals(testProfile.getDetails().getName(), "testName0");
        Assert.assertEquals(testProfile.getDetails().getLocation(), "testLocation0");
        Assert.assertEquals(testProfile.getDetails().getWeb(), "testWeb0");
        Assert.assertEquals(testProfile.getDetails().getBio(), "testBio0");
        testProfile.getDetails().setName("changedName");
        testProfile.getDetails().setLocation("changedLocation");
        testProfile.getDetails().setWeb("changedWeb");
        testProfile.getDetails().setBio("changedBio");
        Assert.assertEquals(testProfile.getDetails().getName(), "changedName");
        Assert.assertEquals(testProfile.getDetails().getLocation(), "changedLocation");
        Assert.assertEquals(testProfile.getDetails().getWeb(), "changedWeb");
        Assert.assertEquals(testProfile.getDetails().getBio(), "changedBio");

    }
    @Test
    void nullTest(){
        Profile profile = new Profile();
        profile.postKweet("test");
        Assert.assertNull(profile.getFollowers());
        Assert.assertNull(profile.getFollowing());
        Assert.assertNull(profile.getKweets());
        Assert.assertNull(profile.getTimeline());
    }
    @Test
    void hearthTest(){
        Profile testProfile = profileList.get(0);
        Kweet kweet = new Kweet("test", testProfile);
        Hearth hearth = new Hearth(testProfile, kweet);
        Assert.assertEquals(hearth, kweet.getCommands().get(0));
    }
    @Test
    void reactTest(){
        Profile testProfile = profileList.get(0);
        Kweet kweet = new Kweet("test", testProfile);
        React reaction = new React("content" ,testProfile,kweet);
        Assert.assertEquals(reaction, kweet.getCommands().get(0));
    }
}