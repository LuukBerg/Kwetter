package kwetter.controller;

import kwetter.model.DTO.KweetDTO;
import kwetter.model.DTO.ProfileDTO;
import kwetter.service.KweetService;
import kwetter.service.ProfileService;
import kwetter.service.UserService;
import kwetter.model.models.Details;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
import kwetter.model.models.Kweet;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Stateless
@Path("/profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecureAuth
public class ProfileController {
    @Inject
    private ProfileService profileService;

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;


    @Context
    private SecurityContext securityContext;

    @POST
    public Profile post(@QueryParam("userid") long userid){
        Profile profile = new Profile(userService.findById(userid),new Details("","", "", ""));
        profileService.create(profile);
        return profile;
    }
    @GET
    @Path("/{id}/kweet")
    public List<KweetDTO> getKweetByProfile(@PathParam("id") long id){

        return KweetDTO.transform(kweetService.findByProfile(id));
    }

    @GET
    @Path("/{id}")
    public ProfileDTO getProfileById(@PathParam("id") long id){
        return ProfileDTO.transform(profileService.findbyId(id));
    }
    @GET
    @Path("/username")
    public Profile getProfileByUsername(@QueryParam("username") String username){
        User user = userService.findByUsername(username);
        System.out.println(user);
        System.out.println(user.getProfile());
        return user.getProfile();
    }

    @GET
    @Path("/{id}/followers")

    public List<ProfileDTO> getFollowers(@PathParam("id") long id){

        return ProfileDTO.transform(profileService.getFollowers(id));
    }

    @GET
    @Path("/{id}/following")
    public List<ProfileDTO> getFollowing(@PathParam("id") long id){

        return ProfileDTO.transform(profileService.getFollowing(id));
    }

    @PUT
    @Path("/follow/{followingid}")
    public void follow(@PathParam("followingid") long followingId){
        User user = userService.findByUsername(securityContext.getUserPrincipal().getName());
        profileService.addFollow(user.getProfile().getId() ,followingId);
    }

    @PUT
    @Path("/unfollow/{followingId}")
    public void unFollow(@PathParam("followingId") long followingId){
        User user = userService.findByUsername(securityContext.getUserPrincipal().getName());
        profileService.unFollow(user.getProfile().getId(), followingId);
    }
}
