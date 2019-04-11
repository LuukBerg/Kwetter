package kwetter.test.APITests;


import kwetter.model.DTO.KweetDTO;
import kwetter.model.DTO.ProfileDTO;
import kwetter.model.DTO.RegisterDTO;
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

        RegisterDTO dto = new RegisterDTO("username","email","password","test","test","test","test");
        Entity json = Entity.json(dto);
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
        RegisterDTO dtoFollower = new RegisterDTO("usernameFollower","email","password","test","test","test","test");
        Entity jsonFollower = Entity.json(dtoFollower);
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
        RegisterDTO dtoFollowing = new RegisterDTO("usernameFollowing","email","password","test","test","test","test");
        Entity jsonFollowing = Entity.json(dtoFollowing);
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
        ProfileDTO profileDTO = response.readEntity(ProfileDTO.class);


        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileDTO.getId()+ "/kweet");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken).get();
        Assert.assertEquals(200, response.getStatus());
        List<Kweet> profileKweets = response.readEntity(new GenericType<List<Kweet>>() {});


        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollower");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
        Assert.assertEquals(200, response.getStatus());
        ProfileDTO profileFollowerDTO = response.readEntity(ProfileDTO.class);

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollowing");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowingToken).get();
        Assert.assertEquals(200, response.getStatus());
        ProfileDTO profileFollowingDTO = response.readEntity(ProfileDTO.class);

        //sends tweets for user
        for (int i = 0 ; i <20 ; i++) {
            client = new ResteasyClientBuilder().build();
            target = client.target(baseUrl + "/kweet");
            builder = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken);
            KweetDTO kweetDTO = new KweetDTO("content: " + i + "profile: " + profileDTO.getId());
            json = Entity.json(kweetDTO);
            response = builder.post(json);
            Assert.assertEquals(200, response.getStatus());
        }
        //sends tweets for follower
        for (int i = 0 ; i <20 ; i++) {
            client = new ResteasyClientBuilder().build();
            target = client.target(baseUrl + "/kweet");
            builder = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken);
            KweetDTO kweetDTO = new KweetDTO("content:  " + i + "profile: ");
            json = Entity.json(kweetDTO);
            response = builder.post(json);
            Assert.assertEquals(200, response.getStatus());
        }
        //send tweets for following
        for (int i = 0 ; i <20 ; i++) {
            client = new ResteasyClientBuilder().build();
            target = client.target(baseUrl + "/kweet");
            builder = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowingToken);
            KweetDTO kweetDTO = new KweetDTO("content: " + i + "profile: " + profileFollowingDTO.getId());
            json = Entity.json(kweetDTO);
            response = builder.post(json);
            Assert.assertEquals(200, response.getStatus());
        }

        //trying wrong token

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/kweet");
        builder = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer 1234" + userToken);
        KweetDTO kweetDTO = new KweetDTO("content: " + "profile: " + profileFollowingDTO.getId());
        json = Entity.json(kweetDTO);
        response = builder.post(json);
        Assert.assertEquals(403, response.getStatus());

        //Het volgen van een gebruiker via de REST api wordt correct bewaard, waarbij als A een volger is van B, beide een referentie naar elkaar hebben: A heeft B in following, en B heeft A in follower.”



        //follow profile

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollower");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowerDTO = response.readEntity(ProfileDTO.class);

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollowing");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowingToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowingDTO = response.readEntity(ProfileDTO.class);

        //get followers and following
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollowingDTO.getId() + "/followers");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowingToken).get();
        Assert.assertEquals(200, response.getStatus());
        List<ProfileDTO> followingFollowersDTOs = response.readEntity(new GenericType<List<ProfileDTO>>() {});



        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollowerDTO.getId() + "/following");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
        Assert.assertEquals(200, response.getStatus());
        List<ProfileDTO> followerFollowingDTOs = response.readEntity(new GenericType<List<ProfileDTO>>() {});



        Assert.assertEquals(0, followingFollowersDTOs.size());
        Assert.assertEquals(0, followerFollowingDTOs.size());

        //"/{id}/follow/{followingid}")
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/follow/" + profileFollowingDTO.getId());
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).put(json);
        Assert.assertEquals(204, response.getStatus());

        //refresh profiles
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollower");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowerDTO = response.readEntity(ProfileDTO.class);

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/username/?username=usernameFollowing");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
        Assert.assertEquals(200, response.getStatus());
        profileFollowingDTO = response.readEntity(ProfileDTO.class);

        //get followers and following
        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollowingDTO.getId() + "/followers");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowingToken).get();
        Assert.assertEquals(200, response.getStatus());
        followingFollowersDTOs = response.readEntity(new GenericType<List<ProfileDTO>>() {});

        client = new ResteasyClientBuilder().build();
        target = client.target(baseUrl + "/profile/"+ profileFollowerDTO.getId() + "/following");
        response = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + userFollowerToken).get();
        Assert.assertEquals(200, response.getStatus());
        followerFollowingDTOs = response.readEntity(new GenericType<List<ProfileDTO>>() {});

        Assert.assertEquals(1, followerFollowingDTOs.size());
        Assert.assertEquals(1, followingFollowersDTOs.size());



       /* target = client.target(baseUrl + "/profile/username/?username=username");
        Response kweets = target.request().get();
        List<Kweet> kweetlist = kweets.readEntity(new GenericType<List<Kweet>>() {});
        for(Kweet kweet: kweetlist){
            System.out.println(kweet.getContent());
        }*/
    }
}