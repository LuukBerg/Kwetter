package kwetter.controller;

import kwetter.JWT.JwtManager;
import kwetter.model.DTO.KweetDTO;
import kwetter.model.models.User;
import kwetter.service.KweetService;
import kwetter.service.ProfileService;
import kwetter.service.UserService;
import kwetter.model.models.Profile;
import kwetter.model.models.Kweet;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Stateless
@Path("/kweet")
@Produces({MediaType.APPLICATION_JSON})
@SecureAuth
public class KweetController {

    @Context
    private SecurityContext securityContext;

    @Inject
    private KweetService kweetService;

    @Inject
    private ProfileService profileService;

    @Inject
    private UserService userService;

    @GET
    public Response get(){
        List<Kweet> kweets = kweetService.getAllOrderedByDate();
        return Response.ok(kweets).header("count", kweets.size()).build();
    }

    @GET
    @Path("/{id}/{offset}")
    public List<KweetDTO> getTimeline(@PathParam("id") long profileId, @PathParam("offset") int offset){
        return KweetDTO.transform(kweetService.getTimeline(profileId, offset));
    }



    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public KweetDTO post(KweetDTO kweetDTO){
            User user = userService.findByUsername(securityContext.getUserPrincipal().getName());
            if(user == null){
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
            System.out.println(securityContext.getUserPrincipal().getName());
            Kweet kweet = new Kweet(kweetDTO.getContent(), user.getProfile());
            return KweetDTO.transform(kweetService.create(kweet));
        //TODO throw error
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id){

            kweetService.delete(id);


    }
    @GET
    @Path("/{id}")
    public Kweet getKweetById(@PathParam("id") long id){
        Kweet kweet = kweetService.findById(id);
        if(kweet!=null){
            return kweet;
        }
        else{
            //todo trhow error
            return null;
        }
    }

}
