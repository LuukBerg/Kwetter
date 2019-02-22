package Kwetter.test.ModelTests;




import Kwetter.model.Commands.Hearth;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.Details;
import Kwetter.model.Models.Kweet;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class ProfileTest {

    private List<Profile> profileList;
    //(User owner, Image image, List<Profile> following, List<Profile> followers, Details details, List<Kweet> kweets)
    // String name, String location, String web, String bio
    @Before
    public void beforeEach() throws IOException {
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
    public void followProfile() {

        Profile profile = profileList.get(0);
        Profile followingProfile = profileList.get(1);
        Assert.assertEquals(profile.getFollowers().size(), 0);
        Assert.assertEquals(followingProfile.getFollowing().size(), 0);
        profile.addFollower(followingProfile);
        followingProfile.addFollowing(profile);
        Assert.assertEquals(profile.getFollowers().get(0), followingProfile);
        Assert.assertEquals(followingProfile.getFollowing().get(0), profile);
    }
    @Test
    public void unfollowProfile(){
        Profile profile = profileList.get(0);
        Profile followingProfile = profileList.get(1);
        Assert.assertEquals(profile.getFollowers().size(), 0);
        Assert.assertEquals(followingProfile.getFollowing().size(), 0);
        profile.addFollower(followingProfile);
        followingProfile.addFollowing(profile);
        Assert.assertEquals(profile.getFollowers().get(0), followingProfile);
        Assert.assertEquals(followingProfile.getFollowing().get(0), profile);


        profile.removeFollower(followingProfile);
        followingProfile.removeFollower(profile);
        Assert.assertEquals(0, profile.getFollowers().size());
        Assert.assertEquals(0,followingProfile.getFollowers().size());
    }

    @Test
    public void getTimeLine(){
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
    public void postKweet() {
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
    public void detailsTest(){
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
    public void nullTest(){
        Profile profile = new Profile();
        Assert.assertNull(profile.getFollowers());
        Assert.assertNull(profile.getFollowing());
        Assert.assertNull(profile.getKweets());
        Assert.assertNull(profile.getTimeline());
    }
    @Test
    public void hearthTest(){
        Profile testProfile = profileList.get(0);
        Kweet kweet = new Kweet("test", testProfile);
        Hearth hearth = new Hearth(testProfile);
        kweet.addHearht(hearth);
        Assert.assertEquals(hearth, kweet.getHearths().get(0));
    }
}