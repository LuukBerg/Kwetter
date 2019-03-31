package kwetter.controller;


import kwetter.model.DTO.RegisterDTO;
import kwetter.model.DTO.UserDTO;
import kwetter.model.enums.Role;
import kwetter.model.models.Details;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
import kwetter.service.UserService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Stateless
@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
public class UserController {
    @Resource
    private SessionContext context;

    @Inject
    private UserService userService;


    @POST
    @Path("/{id}")
    public void updateRole(@QueryParam("role") Role role, @PathParam("id") long id){
        User user = userService.findById(id);
        if(user != null){
            userService.updateRole(role, id);
        }
    }
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public User post(RegisterDTO registerDTO){
        User user = new User(registerDTO.getUsername(),registerDTO.getEmail(),registerDTO.getPassword());
        Profile profile = new Profile(user,new Details(registerDTO.getName(),registerDTO.getBio(),registerDTO.getWeb(),registerDTO.getLocation()));
        user.setProfile(profile);
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
    @Path("/search/{partialName}")
    public List<UserDTO> search(@PathParam("partialName") String partialName){
        List<User> users = userService.findPartialUsername(partialName);
        if(!users.isEmpty())return UserDTO.transform(users);
        else return Collections.emptyList();
    }
    @GET
    @Path("/id")
    public User getUserById(@QueryParam("id") long id){
        return userService.findById(id);
    }
}
