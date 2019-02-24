package kwetter.test.MemoryContextTests;


import kwetter.dao.icontext.IKweetContext;
import kwetter.dao.icontext.IProfileContext;
import kwetter.dao.icontext.IUserContext;
import kwetter.dao.mockcontext.MemoryKweetContext;
import kwetter.dao.mockcontext.MemoryProfileContext;
import kwetter.dao.mockcontext.MemoryUserContext;
import kwetter.model.enums.Role;
import kwetter.model.models.Details;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
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
        user = new User("testUser", Role.USER);
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