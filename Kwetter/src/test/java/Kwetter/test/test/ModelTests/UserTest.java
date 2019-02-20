package Kwetter.test.test.ModelTests;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.Details;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.User;



class UserTest {

    User testUser;
    @BeforeEach
    void beforeEach(){
        testUser = new User("testUsername", Role.user);
        testUser.setProfile(new Profile(testUser,new Details("testName" , "testLocation","testWeb", "testBio")));
    }
    @Test
    void getUsername() {
        Assert.assertEquals(testUser.getUsername(), "testUsername");
    }

    @Test
    void setUsername() {
        Assert.assertEquals(testUser.getUsername(), "testUsername");
        testUser.setUsername("changedUsername");
        Assert.assertEquals(testUser.getUsername(), "changedUsername");
    }

    @Test
    void getRole() {
        Assert.assertEquals(testUser.getRole(), Role.user);
    }

    @Test
    void setRole() {
        Assert.assertEquals(testUser.getRole(), Role.user);
        testUser.setRole(Role.mod);
        Assert.assertEquals(testUser.getRole(), Role.mod);
    }

    @Test
    void getProfile() {
        Assert.assertNotNull(testUser.getProfile());
    }

    @Test
    void setProfile() {
        Profile testProfile = new Profile(testUser,new Details("testName" , "testLocation","testWeb", "testBio"));
        Assert.assertEquals(testUser.getProfile().getDetails().getName(), testProfile.getDetails().getName());
        Profile changedProfile = new Profile(testUser,new Details("changedName" , "changedLocation","changedWeb", "changedBio"));
        testUser.setProfile(changedProfile);
        Assert.assertEquals(testUser.getProfile(), changedProfile);
    }
}