package kwetter.test.ModelTests;


import kwetter.model.models.Kweet;
import kwetter.model.models.Profile;
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
        testKweet = new Kweet("test kweet", new Profile());
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