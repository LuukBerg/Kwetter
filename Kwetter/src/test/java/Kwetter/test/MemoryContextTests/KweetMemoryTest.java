

package Kwetter.test.MemoryContextTests;


import Kwetter.dao.IContext.IKweetContext;
import Kwetter.dao.IContext.IProfileContext;
import Kwetter.dao.IContext.IUserContext;
import Kwetter.dao.MockContext.MemoryKweetContext;
import Kwetter.dao.MockContext.MemoryProfileContext;
import Kwetter.dao.MockContext.MemoryUserContext;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.Details;
import Kwetter.model.Models.Kweet;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.User;

import org.junit.Assert;
import org.junit.Before;
import java.util.List;
import org.junit.Test;

public class KweetMemoryTest {


    private IKweetContext kweetContext;
    private IUserContext userContext;
    private IProfileContext profileContext;
    private Profile profile;
    public KweetMemoryTest() {


    }

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
    public void createKweet(){

        Kweet kweet = new Kweet("testkweet", profile);
        kweetContext.create(kweet);
        Assert.assertEquals(1,kweet.getId());

    }
    @Test
    public void findKweetByProfile() {

        for (int i =0; i <10 ; i++){

            Kweet kweet = new Kweet("testkweet" + i, profile);
            kweetContext.create(kweet);

        }
        List<Kweet> kweets = kweetContext.findByProfile(profile.getId());
        Assert.assertEquals(10, kweets.size());
    }
    @Test
    public void findKweetById(){
        Kweet kweet = new Kweet("testkweet", profile);
        kweetContext.create(kweet);
        Assert.assertEquals(1,kweet.getId());
        Kweet findkweet = kweetContext.findKweetById(1);
        Assert.assertEquals(kweet, findkweet);
    }
    @Test
    public void updateKweet(){
        Kweet kweet = new Kweet("testkweet", profile);
        kweetContext.create(kweet);
        Assert.assertEquals(1,kweet.getId());
        kweet.setContent("updated");
        Kweet updateKweet = kweetContext.update(kweet);
        Assert.assertEquals("updated", updateKweet.getContent());
    }
    @Test
    public void removeKweet(){
        Kweet kweet = new Kweet("testkweet", profile);
        kweetContext.create(kweet);
        Assert.assertEquals(1,kweet.getId());
        kweetContext.deleteById(kweet.getId());
        Kweet found = kweetContext.findKweetById(1);
        Assert.assertNull(found);
    }
    @Test
    public void getTimeline(){
        for(int i =0 ; i < 20; i++){
            Kweet kweet = new Kweet("testkweet" + i, profile);
            kweetContext.create(kweet);
        }
        List<Kweet> timeline = kweetContext.getTimeLine(profile);
        Assert.assertEquals(10, timeline.size());
    }

}