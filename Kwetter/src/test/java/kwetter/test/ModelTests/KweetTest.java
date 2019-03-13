package kwetter.test.ModelTests;


import kwetter.model.models.Details;
import kwetter.model.models.Kweet;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;


public class KweetTest {

    Date date;
    Kweet testKweet;
    @Before
    public void beforeEach() throws IOException { ;
        date = new Date();
        testKweet = new Kweet("test kweet", new Profile(new User("username", "email", "password"), new Details("test","test", "test", "test")));
    }

    @Test
    public void getDate(){
        Assert.assertEquals(testKweet.getDate(), date);
        date.setTime(10);
        Assert.assertNotEquals(testKweet.getDate(), date);
    }

    @Test
    public void getContent() {
        Assert.assertEquals(testKweet.getContent(), "test kweet");
    }
    @Test
    public void nullTest(){
        Kweet kweet = new Kweet();
        Assert.assertNull(kweet.getContent());
        Assert.assertNull(kweet.getDate());

    }




}