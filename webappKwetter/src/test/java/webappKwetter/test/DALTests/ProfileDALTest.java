package webappKwetter.test.DALTests;


import org.junit.Assert;
import webappKwetter.dao.KwetterFacade;
import webappKwetter.dao.MockContext.MockProfileContext;
import webappKwetter.model.Models.Details;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import webappKwetter.dao.MockContext.MockUserContext;

class ProfileDALTest {

    KwetterFacade facade;

    @BeforeEach
    public  void beforeEach() throws IOException {
        facade = new KwetterFacade(new MockUserContext(), new MockProfileContext());
        facade.loginUser("testUser1");
    }


    @Test
    void followProfile() {
        Profile followProfile = facade.getProfile("testUser2");
        Assert.assertFalse(facade.getUser().getProfile().getFollowing().contains(followProfile));
        Assert.assertFalse(followProfile.getFollowers().contains(facade.getUser()));
        facade.followProfile(followProfile);
        Assert.assertTrue(facade.getUser().getProfile().getFollowing().contains(followProfile));
        Assert.assertTrue(followProfile.getFollowers().contains(facade.getUser()));
    }

    @Test
    void getTimeLine(){
        List<Kweet> timeline = facade.getUser().getProfile().getTimeline();
        Assert.assertEquals(timeline.size(), 10);

    }

    @Test
    void postKweet() {
        List<Kweet> kweets = facade.getUser().getProfile().getKweets();
        Assert.assertEquals(kweets.size(), 10);
        facade.postKweet("test kweet content");
        Assert.assertEquals(kweets.size(), 11);
        Assert.assertEquals(kweets.get(kweets.size() -1 ).getContent(), "test kweet content");
    }
    @Test
    void detailsTest(){
        Details details = facade.getUser().getProfile().getDetails();
        Assert.assertEquals(details.getName(), "testName0");
        Assert.assertEquals(details.getLocation(), "testLocation0");
        Assert.assertEquals(details.getWeb(), "testWeb0");
        Assert.assertEquals(details.getBio(), "testBio0");
        facade.setDetails(new Details("test", "test", "test", "test"));
        Assert.assertEquals(details.getName(), "test");
        Assert.assertEquals(details.getLocation(), "test");
        Assert.assertEquals(details.getWeb(), "test");
        Assert.assertEquals(details.getBio(), "test");


    }
}