package Kwetter.Controller;

import Kwetter.dao.Repo.KweetService;
import Kwetter.dao.Repo.ProfileService;
import Kwetter.dao.Repo.UserService;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.Kweet;

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
            kweetService.delete(id, profile);
        }
        else{
            //throw error
        }


    }

}
