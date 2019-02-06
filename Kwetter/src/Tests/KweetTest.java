package Tests;

import Enums.Role;
import Models.Details;
import Models.Kweet;
import Models.Profile;
import Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KweetTest {

    Date date;
    Kweet testKweet;
    @BeforeEach
    public  void beforeEach() throws IOException { ;
        date = new Date();
        testKweet = new Kweet("test kweet");
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