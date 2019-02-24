package Kwetter.Controller;

import Kwetter.dao.Service.KweetService;
import Kwetter.dao.Service.ProfileService;
import Kwetter.dao.Service.UserService;
import Kwetter.model.Models.Details;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.User;
import Kwetter.model.Models.Kweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@Path("/Profile")
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
    public Profile getProfileByUsername(@QueryParam("username") String username){
        User user = userService.findByUsername(username);
        return user.getProfile();
    }
    @PUT
    @Path("/{id}/follow/{followingId}")
    public void updateFollowing(@PathParam("id") long id, @PathParam("followingId") long followingId){
        profileService.addFollow(id ,followingId);
    }
    @PUT
    @Path("/{id}/unfollow/{followingId}")
    public void unFollow(@PathParam("id") long id, @PathParam("followingId") long followingId){
        profileService.unFollow(id, followingId);
    }
}
