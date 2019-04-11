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
import javax.ws.rs.core.UriInfo;
import java.net.URI;
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
    public ProfileDTO getProfileById(@PathParam("id") long id, @Context UriInfo uriInfo){
        User user = userService.findByUsername(securityContext.getUserPrincipal().getName());
        ProfileDTO dto = ProfileDTO.transform(profileService.findbyId(id));

        dto.addLink(getUriForProfile(uriInfo, id, "getProfileById"), "self");
        dto.addLink(getUriForProfile(uriInfo, id,"getFollowers"), "getFollowers");
        dto.addLink(getUriForProfile(uriInfo, id, "getFollowing"), "getFollowing");
        dto.addLink(getUriForProfile(uriInfo, id, "getKweetByProfile"), "getKweets");
        if(user.getProfile().getId() != dto.getId()){
            dto.addLink(getUriForProfile(uriInfo, id, "follow"), "follow");
            dto.addLink(getUriForProfile(uriInfo, id, "unFollow"), "unfollow");
        }
        return dto;

    }
    @GET
    @Path("/username")
    public ProfileDTO getProfileByUsername(@QueryParam("username") String username, @Context UriInfo uriInfo){
        User user = userService.findByUsername(username);
        ProfileDTO dto = ProfileDTO.transform(user.getProfile());
        dto.addLink(getUriForProfile(uriInfo, dto.getId(), "getProfileById"), "self");
        dto.addLink(getUriForProfile(uriInfo, dto.getId(),"getFollowers"), "getFollowers");
        dto.addLink(getUriForProfile(uriInfo, dto.getId(), "getFollowing"), "getFollowing");
        return dto;
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
    @Path("/follow/{id}")
    public void follow(@PathParam("id") long followingId){
        User user = userService.findByUsername(securityContext.getUserPrincipal().getName());
        profileService.addFollow(user.getProfile().getId() ,followingId);
    }

    @PUT
    @Path("/unfollow/{id}")
    public void unFollow(@PathParam("id") long followingId){
        User user = userService.findByUsername(securityContext.getUserPrincipal().getName());
        profileService.unFollow(user.getProfile().getId(), followingId);
    }

    private String getUriForProfile(UriInfo uriInfo, Long id, String method){
        URI uri = uriInfo.getBaseUriBuilder()
                .path(ProfileController.class)
                .path(ProfileController.class, method)
                .resolveTemplate("id", id)
                .build();
        return uri.toString();
    }


}
