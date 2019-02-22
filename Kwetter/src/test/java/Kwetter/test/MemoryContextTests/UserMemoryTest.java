

package Kwetter.test.MemoryContextTests;


import Kwetter.dao.IContext.IKweetContext;
import Kwetter.dao.IContext.IProfileContext;
import Kwetter.dao.IContext.IUserContext;
import Kwetter.dao.KwetterFacade;
import Kwetter.dao.MockContext.MemoryKweetContext;
import Kwetter.dao.MockContext.MemoryProfileContext;
import Kwetter.dao.MockContext.MemoryUserContext;
import Kwetter.dao.MySqlContext.MySQLUserContext;
import Kwetter.dao.Service.UserService;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.Details;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.User;

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
        User user = new User("testUser", Role.user);
        userContext.create(user);
        profile = new Profile(user, new Details("test","test","test", "test"));
        profileContext.create(profile);
    }
    @Test
    public void registerUser(){

        User user = userContext.create(new User("testuser", Role.user));
        Assert.assertEquals(1,user.getId());
        User user2 = userContext.create(new User("testuser2", Role.user));
        Assert.assertEquals(2,user2.getId());
    }
    @Test
    public void loginUser() {
        User user = userContext.create(new User("testuser", Role.user));

        User loginUser = userContext.findByUsername("testuser");
        Assert.assertEquals(user, loginUser);
    }
    @Test
    public void updateUser(){
        User user = userContext.create(new User("testuser", Role.user));

        User loginUser = userContext.findByUsername("testuser");
        Assert.assertEquals(user, loginUser);

        loginUser.setUsername("testuserchanged");
        userContext.update(loginUser);
        User updatedUser = userContext.findByUsername("testuserchanged");
        Assert.assertNotNull(updatedUser);
    }
    @Test
    public void findById(){
        User user = userContext.create(new User("testuser", Role.user));

        User found = userContext.findbyId(1);
        Assert.assertEquals(user, found);
    }
    @Test
    public void removeUser(){
        User user = userContext.create(new User("testuser", Role.user));

        userContext.deleteById(user.getId());
        User found = userContext.findbyId(user.getId());
        Assert.assertNull(found);
    }


}