package kwetter.controller;

import kwetter.service.KweetService;
import kwetter.service.ProfileService;
import kwetter.service.UserService;
import kwetter.model.models.Profile;
import kwetter.model.models.Kweet;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@Path("/kweet")
public class KweetController {

    @Resource
    private SessionContext sessionContext;

    @Inject
    private KweetService kweetService;

    @Inject
    private ProfileService profileService;

    @Inject
    private UserService userService;

    @GET
    public List<Kweet> get(){
        return kweetService.getAllOrderedByDate();
    }

    @POST
    public Kweet post(@QueryParam("message") String message){
        Profile profile = userService.findByUsername(sessionContext.getCallerPrincipal().getName()).getProfile();
        if(profile != null){
            return kweetService.create(new Kweet(message, profile));
        }
        //TODO throw error
        return null;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id){
        Profile profile = userService.findByUsername(sessionContext.getCallerPrincipal().getName()).getProfile();
        if(profile != null){
            kweetService.delete(id);
        }
        else{
            //TODO throw error
        }


    }

}
