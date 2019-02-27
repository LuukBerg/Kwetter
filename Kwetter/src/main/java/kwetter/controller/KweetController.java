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
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/kweet")
@Produces({MediaType.APPLICATION_JSON})
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
    @Consumes({MediaType.APPLICATION_JSON})
    public Kweet post(Kweet kweet){
        //TODO add sessioncontext
        //Profile profile = userService.findByUsername(sessionContext.getCallerPrincipal().getName()).getProfile();
        //if(profile != null){
            System.out.println("+++++++++++++++++++++++++++++++++++++++++");
            System.out.println(kweet.getOwner());
            return kweetService.create(kweet);
        //}
        //TODO throw error
        //return null;
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
