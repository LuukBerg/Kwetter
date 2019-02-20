package Kwetter.Controller;

import Kwetter.dao.Repo.KweetService;
import Kwetter.dao.Repo.ProfileService;
import Kwetter.dao.Repo.UserService;
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
