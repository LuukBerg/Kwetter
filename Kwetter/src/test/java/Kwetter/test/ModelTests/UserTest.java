package Kwetter.test.ModelTests;




import Kwetter.model.Enums.Role;
import Kwetter.model.Models.Details;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class UserTest {

    User testUser;
    @Before
    public void beforeEach(){
        testUser = new User("testUsername", Role.user);
        testUser.setProfile(new Profile(testUser,new Details("testName" , "testLocation","testWeb", "testBio")));
    }
    @Test
    public void getUsername() {
        Assert.assertEquals(testUser.getUsername(), "testUsername");
    }

    @Test
    public void setUsername() {
        Assert.assertEquals(testUser.getUsername(), "testUsername");
        testUser.setUsername("changedUsername");
        Assert.assertEquals(testUser.getUsername(), "changedUsername");
    }

    @Test
    public void getRole() {
        Assert.assertEquals(testUser.getRole(), Role.user);
    }

    @Test
    public void setRole() {
        Assert.assertEquals(testUser.getRole(), Role.user);
        testUser.setRole(Role.mod);
        Assert.assertEquals(testUser.getRole(), Role.mod);
    }

    @Test
    public void getProfile() {
        Assert.assertNotNull(testUser.getProfile());
    }

    @Test
    public void setProfile() {
        Profile testProfile = new Profile(testUser,new Details("testName" , "testLocation","testWeb", "testBio"));
        Assert.assertEquals(testUser.getProfile().getDetails().getName(), testProfile.getDetails().getName());
        Profile changedProfile = new Profile(testUser,new Details("changedName" , "changedLocation","changedWeb", "changedBio"));
        testUser.setProfile(changedProfile);
        Assert.assertEquals(testUser.getProfile(), changedProfile);
    }
}