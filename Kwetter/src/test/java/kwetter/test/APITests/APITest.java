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
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

public class APITest {

    private static String baseUrl = "http://localhost:8080/Kwetter/api";
    @Test
    public void createTestEnv(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        WebTarget target = client.target(baseUrl + "/user");
        Invocation.Builder builder = target.request();
        User user = new User("username","email", "password");
        Profile profile1 = new Profile(user,new Details("test", "test", "test", "test"));
        Entity json = Entity.json(profile1);
        Response response = builder.post(json);
        Assert.assertEquals(200, response.getStatus());

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=username");
        response = target.request().get();
        Assert.assertEquals(200, response.getStatus());
        Profile profile = response.readEntity(Profile.class);
        //TODO send kweets
        for (int i = 0 ; i <20 ; i++) {


            target = client.target(baseUrl + "/kweet");
            builder = target.request();
            json = Entity.json(new Kweet("testcontent" + i, profile));
            response = builder.post(json);
            Assert.assertEquals(200, response.getStatus());
        }

        //Het volgen van een gebruiker via de REST api wordt correct bewaard, waarbij als A een volger is van B, beide een referentie naar elkaar hebben: A heeft B in following, en B heeft A in follower.”

        //create users
        target = client.target(baseUrl + "/user");
        builder = target.request();
        User userFollower = new User("usernameFollower","email", "password");
        Profile profileFollower = new Profile(userFollower,new Details("test", "test", "test", "test"));
        Entity jsonFollower = Entity.json(profileFollower);
        Response responsefollower = builder.post(jsonFollower);
        Assert.assertEquals(200, responsefollower.getStatus());

        target = client.target(baseUrl + "/user");
        builder = target.request();
        User userFollowing = new User("usernameFollowing","email", "password");
        Profile profileFollowing = new Profile(userFollowing,new Details("test", "test", "test", "test"));
        Entity jsonFollowing = Entity.json(profileFollowing);
        Response responsefollowing = builder.post(jsonFollowing);
        Assert.assertEquals(200, responsefollowing.getStatus());

        //follow profile

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollower");
        response = target.request().get();
        Assert.assertEquals(200, response.getStatus());
        profileFollower = response.readEntity(Profile.class);

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollowing");
        response = target.request().get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowing = response.readEntity(Profile.class);

        //get followers and following
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollowing.getId() + "/followers");
        response = target.request().get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowing.setFollowers(response.readEntity(new GenericType<List<Profile>>() {}));

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollower.getId() + "/following");
        response = target.request().get();
        Assert.assertEquals(200, response.getStatus());
        profileFollower.setFollowing(response.readEntity(new GenericType<List<Profile>>() {}));



        Assert.assertEquals(0, profileFollowing.getFollowers().size());
        Assert.assertEquals(0, profileFollower.getFollowing().size());

        //"/{id}/follow/{followingid}")
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollower.getId() +"/follow/" + profileFollowing.getId());
        response = target.request().put(json);
        Assert.assertEquals(204, response.getStatus());

        //refresh profiles
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollower");
        response = target.request().get();
        Assert.assertEquals(200, response.getStatus());
        profileFollower = response.readEntity(Profile.class);

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollowing");
        response = target.request().get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowing = response.readEntity(Profile.class);

        //get followers and following
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollowing.getId() + "/followers");
        response = target.request().get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowing.setFollowers(response.readEntity(new GenericType<List<Profile>>() {}));

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollower.getId() + "/following");
        response = target.request().get();
        Assert.assertEquals(200, response.getStatus());
        profileFollower.setFollowing(response.readEntity(new GenericType<List<Profile>>() {}));

        Assert.assertEquals(1, profileFollower.getFollowing().size());
        Assert.assertEquals(1, profileFollowing.getFollowers().size());



       /* target = client.target(baseUrl + "/profile/username/?username=username");
        Response kweets = target.request().get();
        List<Kweet> kweetlist = kweets.readEntity(new GenericType<List<Kweet>>() {});
        for(Kweet kweet: kweetlist){
            System.out.println(kweet.getContent());
        }*/
    }
}