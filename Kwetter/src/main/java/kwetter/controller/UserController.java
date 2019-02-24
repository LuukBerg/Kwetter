package kwetter.controller;


import kwetter.model.enums.Role;
import kwetter.model.models.User;
import kwetter.dao.service.UserService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/USER")
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
    public User post(@QueryParam("username") String username){
        User user = userService.registerUser(new User(username, Role.USER));
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
