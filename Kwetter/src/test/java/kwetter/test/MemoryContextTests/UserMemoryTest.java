

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


public class UserMemoryTest {


    private IKweetContext kweetContext;
    private IUserContext userContext;
    private IProfileContext profileContext;
    private Profile profile;


    @Before
    public void Before(){
        kweetContext = new MemoryKweetContext();
        profileContext = new MemoryProfileContext();
        userContext = new MemoryUserContext();
        User user = new User("testUser", Role.USER);
        userContext.create(user);
        profile = new Profile(user, new Details("test","test","test", "test"));
        profileContext.create(profile);
    }
    @Test
    public void registerUser(){

        User user = userContext.create(new User("testuser", Role.USER));
        Assert.assertEquals(1,user.getId());
        User user2 = userContext.create(new User("testuser2", Role.USER));
        Assert.assertEquals(2,user2.getId());
    }
    @Test
    public void loginUser() {
        User user = userContext.create(new User("testuser", Role.USER));

        User loginUser = userContext.findByUsername("testuser");
        Assert.assertEquals(user, loginUser);
    }
    @Test
    public void updateUser(){
        User user = userContext.create(new User("testuser", Role.USER));

        User loginUser = userContext.findByUsername("testuser");
        Assert.assertEquals(user, loginUser);

        loginUser.setUsername("testuserchanged");
        userContext.update(loginUser);
        User updatedUser = userContext.findByUsername("testuserchanged");
        Assert.assertNotNull(updatedUser);
    }
    @Test
    public void findById(){
        User user = userContext.create(new User("testuser", Role.USER));

        User found = userContext.findbyId(1);
        Assert.assertEquals(user, found);
    }
    @Test
    public void removeUser(){
        User user = userContext.create(new User("testuser", Role.USER));

        userContext.deleteById(user.getId());
        User found = userContext.findbyId(user.getId());
        Assert.assertNull(found);
    }


}