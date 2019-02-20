package Kwetter.Controller;

import Kwetter.model.Enums.Role;
import Kwetter.model.Models.User;
import Kwetter.dao.Repo.UserService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Stateless
@Path("/user")
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
}
