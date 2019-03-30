package kwetter.test.APITests;


import kwetter.model.DTO.KweetDTO;
import kwetter.model.models.Details;
import kwetter.model.models.Kweet;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import java.util.List;

public class APITest {

    private static String baseUrl = "http://localhost:18080/Kwetter/api";
    @Test
    public void createTestEnv() throws ParseException {


        String userToken = null;
        String userFollowerToken = null;
        String userFollowingToken = null;

        ResteasyClient client = new ResteasyClientBuilder().build();
        WebTarget target = client.target(baseUrl + "/user");
        Invocation.Builder builder = target.request();
        User user = new User("username","email", "password");
        Profile profile1 = new Profile(user,new Details("test", "test", "test", "test"));
        Entity json = Entity.json(profile1);
        Response response = builder.post(json);
        Assert.assertEquals(200, response.getStatus());


        //get user token


        target = client.target(baseUrl + "/auth/login");
        Form form = new Form();
        form.param("username", "username").param("password","password");
        Entity<Form> entity = Entity.form(form);
        Response tokenResponse = target.request(MediaType.APPLICATION_JSON_TYPE).post(entity);
        String jsonString = tokenResponse.readEntity(String.class);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
        userToken = jsonObject.getAsString("token");
        Assert.assertNotNull(userToken);


        //create users
        target = client.target(baseUrl + "/user");
        builder = target.request();
        User userFollower = new User("usernameFollower","email", "password");
        Profile profileFollower = new Profile(userFollower,new Details("test", "test", "test", "test"));
        Entity jsonFollower = Entity.json(profileFollower);
        Response responsefollower = builder.post(jsonFollower);
        Assert.assertEquals(200, responsefollower.getStatus());

        target = client.target(baseUrl + "/auth/login");
        form = new Form();
        form.param("username", "usernameFollower").param("password","password");
        entity = Entity.form(form);
        tokenResponse = target.request(MediaType.APPLICATION_JSON_TYPE).post(entity);
        jsonString = tokenResponse.readEntity(String.class);
        jsonObject = (JSONObject) parser.parse(jsonString);
        userFollowerToken = jsonObject.getAsString("token");
        Assert.assertNotNull(userFollowerToken);


        target = client.target(baseUrl + "/user");
        builder = target.request();
        User userFollowing = new User("usernameFollowing","email", "password");
        Profile profileFollowing = new Profile(userFollowing,new Details("test", "test", "test", "test"));
        Entity jsonFollowing = Entity.json(profileFollowing);
        Response responsefollowing = builder.post(jsonFollowing);
        Assert.assertEquals(200, responsefollowing.getStatus());

        target = client.target(baseUrl + "/auth/login");
        form = new Form();
        form.param("username", "usernameFollowing").param("password","password");
        entity = Entity.form(form);
        tokenResponse = target.request(MediaType.APPLICATION_JSON_TYPE).post(entity);
        jsonString = tokenResponse.readEntity(String.class);
        jsonObject = (JSONObject) parser.parse(jsonString);
        userFollowingToken = jsonObject.getAsString("token");
        Assert.assertNotNull(userFollowingToken);

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=username");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken).get();
        Assert.assertEquals(200, response.getStatus());
        Profile profile = response.readEntity(Profile.class);

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profile.getId()+ "/kweet");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken).get();
        Assert.assertEquals(200, response.getStatus());
        List<Kweet> kweets = response.readEntity(new GenericType<List<Kweet>>() {});
        profile.setKweets(kweets);

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollower");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollower = response.readEntity(Profile.class);

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollowing");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowingToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowing = response.readEntity(Profile.class);

        //sends tweets for user
        for (int i = 0 ; i <20 ; i++) {
            client = new ResteasyClientBuilder().build();
            target = client.target(baseUrl + "/kweet");
            builder = target.request();
            KweetDTO kweetDTO = new KweetDTO(0,profile.getId(), "content: " + i + "profile: " + profile.getId(),profile.getOwner().getUsername(),null);
            json = Entity.json(kweetDTO);
            response = builder.post(json);
            Assert.assertEquals(200, response.getStatus());
        }
        //sends tweets for follower
        for (int i = 0 ; i <20 ; i++) {
            client = new ResteasyClientBuilder().build();
            target = client.target(baseUrl + "/kweet");
            builder = target.request();
            KweetDTO kweetDTO = new KweetDTO(0,profileFollower.getId(), "content:  " + i + "profile: " + profileFollower.getId(),profileFollower.getOwner().getUsername(),null);
            json = Entity.json(kweetDTO);
            response = builder.post(json);
            Assert.assertEquals(200, response.getStatus());
        }
        //send tweets for following
        for (int i = 0 ; i <20 ; i++) {
            client = new ResteasyClientBuilder().build();
            target = client.target(baseUrl + "/kweet");
            builder = target.request();
            KweetDTO kweetDTO = new KweetDTO(0,profileFollowing.getId(), "content: " + i + "profile: " + profileFollowing.getId(),profileFollowing.getOwner().getUsername(),null);
            json = Entity.json(kweetDTO);
            response = builder.post(json);
            Assert.assertEquals(200, response.getStatus());
        }

        //Het volgen van een gebruiker via de REST api wordt correct bewaard, waarbij als A een volger is van B, beide een referentie naar elkaar hebben: A heeft B in following, en B heeft A in follower.”



        //follow profile

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollower");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollower = response.readEntity(Profile.class);

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollowing");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowingToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowing = response.readEntity(Profile.class);

        //get followers and following
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollowing.getId() + "/followers");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowingToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowing.setFollowers(response.readEntity(new GenericType<List<Profile>>() {}));

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollower.getId() + "/following");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollower.setFollowing(response.readEntity(new GenericType<List<Profile>>() {}));



        Assert.assertEquals(0, profileFollowing.getFollowers().size());
        Assert.assertEquals(0, profileFollower.getFollowing().size());

        //"/{id}/follow/{followingid}")
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollower.getId() +"/follow/" + profileFollowing.getId());
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).put(json);
        Assert.assertEquals(204, response.getStatus());

        //refresh profiles
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollower");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollower = response.readEntity(Profile.class);

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollowing");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowing = response.readEntity(Profile.class);

        //get followers and following
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollowing.getId() + "/followers");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowingToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowing.setFollowers(response.readEntity(new GenericType<List<Profile>>() {}));

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollower.getId() + "/following");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
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