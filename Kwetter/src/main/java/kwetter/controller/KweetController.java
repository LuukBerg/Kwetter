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
import javax.ws.rs.core.Response;
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
    public Response get(){

        List<Kweet> kweets = kweetService.getAllOrderedByDate();
        return Response.ok(kweets).header("count", kweets.size()).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Kweet post(Kweet kweet){
        //TODO add sessioncontext
            return kweetService.create(kweet);
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
