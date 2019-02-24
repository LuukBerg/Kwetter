package kwetter.test.ModelTests;




import kwetter.model.enums.Role;
import kwetter.model.models.Details;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class UserTest {

    User testUser;
    @Before
    public void beforeEach(){
        testUser = new User("testUsername", Role.USER);
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
        Assert.assertEquals(testUser.getRole(), Role.USER);
    }

    @Test
    public void setRole() {
        Assert.assertEquals(testUser.getRole(), Role.USER);
        testUser.setRole(Role.MOD);
        Assert.assertEquals(testUser.getRole(), Role.MOD);
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