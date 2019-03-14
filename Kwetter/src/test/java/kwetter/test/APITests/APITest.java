package kwetter.test.APITests;


import kwetter.model.models.Details;
import kwetter.model.models.Kweet;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class APITest {

    private static String baseUrl = "http://localhost:8080/Kwetter/api";
    @Test
    public void createTestEnv(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        WebTarget target = client.target(baseUrl + "/user");
        Invocation.Builder builder = target.request();
        User user = new User("username","email", "password");
        Profile profile1 = new Profile(user,new Details("test", "test", "test", "test"));
        Entity json = Entity.json(user);
        System.out.println(json.toString());
        Response response = builder.post(json);
        System.out.println(response.toString());
        Assert.assertEquals(200, response.getStatus());

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=username");
        response = target.request().get();
        Assert.assertEquals(200, response.getStatus());
        Profile profile = response.readEntity(User.class).getProfile();
        //TODO send kweets
        for (int i = 0 ; i <10 ; i++) {


            target = client.target(baseUrl + "/kweet");
            builder = target.request();
            json = Entity.json(new Kweet("testcontent" + i, profile));
            System.out.println(json);
            response = builder.post(json);
            System.out.println(response.toString());
            Assert.assertEquals(200, response.getStatus());
        }

        //Het volgen van een gebruiker via de REST api wordt correct bewaard, waarbij als A een volger is van B, beide een referentie naar elkaar hebben: A heeft B in following, en B heeft A in follower.”

        //create users
        User userFollower = new User("usernameFollower","email", "password");
        Profile profileFollower = new Profile(userFollower,new Details("test", "test", "test", "test"));
        Entity jsonFollower = Entity.json(userFollower);
        Response responsefollower = builder.post(jsonFollower);
        Assert.assertEquals(200, responsefollower.getStatus());

        User userFollowing = new User("usernameFollowing","email", "password")
        Profile profileFollowing = new Profile(userFollowing,new Details("test", "test", "test", "test"));
        Entity jsonFollowing = Entity.json(userFollowing);
        Response responsefollowing = builder.post(jsonFollower);
        Assert.assertEquals(200, responsefollowing.getStatus());

        //follow profile



       /* target = client.target(baseUrl + "/profile/username/?username=username");
        Response kweets = target.request().get();
        List<Kweet> kweetlist = kweets.readEntity(new GenericType<List<Kweet>>() {});
        for(Kweet kweet: kweetlist){
            System.out.println(kweet.getContent());
        }*/
    }
}