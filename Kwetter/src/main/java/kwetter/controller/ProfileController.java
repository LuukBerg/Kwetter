package kwetter.controller;

import kwetter.service.KweetService;
import kwetter.service.ProfileService;
import kwetter.service.UserService;
import kwetter.model.models.Details;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
import kwetter.model.models.Kweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileController {
    @Inject
    private ProfileService profileService;

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    @POST
    public Profile post(@QueryParam("userid") long userid){
        Profile profile = new Profile(userService.findById(userid),new Details("","", "", ""));
        profileService.create(profile);
        return profile;
    }
    @GET
    @Path("/{id}/kweet")
    public List<Kweet> getKweetByProfile(@PathParam("id") long id){
        return kweetService.findByProfile(id);
    }

    @GET
    @Path("/username")
    public User getProfileByUsername(@QueryParam("username") String username){
        User user = userService.findByUsername(username);
        return user;
    }
    @GET
    @Path("/{id}/followers")
    public List<Profile> getFollowers(@PathParam("id") long id){
        return profileService.getFollowers(id);
    }
    @GET
    @Path("/{id}/following")
    public List<Profile> getFollowing(@PathParam("id") long id){
        return profileService.getFollowing(id);
    }
    @PUT
    @Path("/{id}/follow/{followingid}")
    public void Follow(@PathParam("id") long id, @PathParam("followingid") long followingId){
        profileService.addFollow(id ,followingId);
    }
    @PUT
    @Path("/{id}/unfollow/{followingId}")
    public void unFollow(@PathParam("id") long id, @PathParam("followingId") long followingId){
        profileService.unFollow(id, followingId);
    }
}
