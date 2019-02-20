package Kwetter.test.ModelTests;


import org.junit.Assert;
import Kwetter.model.Models.Kweet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Kwetter.model.Models.Profile;

import java.io.IOException;
import java.util.Date;


class KweetTest {

    Date date;
    Kweet testKweet;
    @BeforeEach
    public  void beforeEach() throws IOException { ;
        date = new Date();
        testKweet = new Kweet("test kweet", new Profile());
    }

    @Test
    void getDate(){
        Assert.assertEquals(testKweet.getDate(), date);
        date.setTime(10);
        Assert.assertNotEquals(testKweet.getDate(), date);
    }

    @Test
    void getContent() {
        Assert.assertEquals(testKweet.getContent(), "test kweet");
    }
    @Test
    void nullTest(){
        Kweet kweet = new Kweet();
        Assert.assertNull(kweet.getContent());
        Assert.assertNull(kweet.getDate());

    }



}