package Kwetter.test.MemoryContextTests;


import Kwetter.dao.IContext.IKweetContext;
import Kwetter.dao.IContext.IProfileContext;
import Kwetter.dao.IContext.IUserContext;
import Kwetter.dao.KwetterFacade;
import Kwetter.dao.MockContext.MemoryKweetContext;
import Kwetter.dao.MockContext.MemoryProfileContext;
import Kwetter.dao.MockContext.MemoryUserContext;
import Kwetter.dao.MySqlContext.MySQLProfileContext;
import Kwetter.dao.Service.ProfileService;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.Details;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ProfileMemoryTest {

    private IKweetContext kweetContext;
    private IUserContext userContext;
    private IProfileContext profileContext;
    private Profile profile;
    private User user;
    @Before
    public void Before(){
        kweetContext = new MemoryKweetContext();
        profileContext = new MemoryProfileContext();
        userContext = new MemoryUserContext();
        user = new User("testUser", Role.user);
        userContext.create(user);
        profile = new Profile(user, new Details("test","test","test", "test"));
        profileContext.create(profile);
    }


    @Test
    public void createProfile(){
        Profile profile = new Profile(user, new Details("test", "test", "test","test"));
        profileContext.create(profile);

        Assert.assertEquals(1, profile.getId());
    }
    @Test
    public void updateProfile(){

        Profile profile = new Profile(user, new Details("test", "test", "test","test"));
        profileContext.create(profile);
        Assert.assertEquals(1, profile.getId());
        Assert.assertEquals("test", profile.getDetails().getName());
        profile.getDetails().setName("updatename");
        profileContext.update(profile);
        Profile getProfile = profileContext.findbyId(profile.getId());
        Assert.assertEquals("updatename", getProfile.getDetails().getName());
    }
    @Test
    public void removeProfile(){
        Profile profile = new Profile(user, new Details("test", "test", "test","test"));
        profileContext.create(profile);
        Assert.assertEquals(1, profile.getId());
        Assert.assertEquals("test", profile.getDetails().getName());
        profileContext.deleteById(profile.getId());
        Profile found = profileContext.findbyId(1);
        Assert.assertNull(found);

    }
}