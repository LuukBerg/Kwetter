package kwetter.controller;


import kwetter.model.enums.Role;
import kwetter.model.models.User;
import kwetter.service.UserService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

@Stateless
@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
public class UserController {
    @Resource
    private SessionContext context;

    @Inject
    private UserService userService;


    @PUT
    @Path("/{id}")
    public void updateRole(@QueryParam("role") Role role, @PathParam("id") long id){
        User user = userService.findById(id);
        if(user != null){
            userService.updateRole(role, user);
        }
    }
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public User post(//@QueryParam("username") String username
                      User user){

        user = userService.registerUser(user);
        if(user != null){
            return user;
        }
        return null;
    }

    @GET
    @Path("/username")
    public User getProfileByUsername(@QueryParam("username") String username){
        return userService.findByUsername(username);

    }

    @GET
    @Path("/id")
    public User getUserById(@QueryParam("id") long id){
        return userService.findById(id);
    }
}
