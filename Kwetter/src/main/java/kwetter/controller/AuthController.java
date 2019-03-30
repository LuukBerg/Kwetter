package kwetter.controller;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import kwetter.JWT.Jwt;
import kwetter.JWT.JwtManager;
import kwetter.bean.UserServiceBean;
import kwetter.model.DTO.UserDTO;
import kwetter.model.models.User;
import kwetter.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.logging.Logger;

@Stateless
@Path("/auth")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class AuthController {
    private static final Logger log = Logger.getLogger(AuthController.class.getName());

    @EJB
    UserServiceBean userServiceBean;

    @Inject
    private UserService userService;
    @Inject
    JwtManager jwtManager;

    @GET
    @Path("/test")
    public Response test(@HeaderParam("Authorization") String auth){
        if (auth != null && auth.startsWith("Bearer ")) {
            try {
                JWT j = JWTParser.parse(auth.substring(7));
                return Response.ok(j.getJWTClaimsSet().getClaims()).build(); //Note: nimbusds converts token expiration time to milliseconds
            } catch (ParseException e) {
                log.warning(e.toString());
                return Response.status(400).build();
            }
        }
        return Response.status(204).build(); //no jwt means no claims to extract
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postJWT(@FormParam("username") String username, @FormParam("password") String password){
        log.info("authenticating: " + username);
        try{
            User user = userService.loginUser(username,password);
            if(user != null){
                log.info("Generating JWT for user: " + user.getUsername());
            }
            String token = jwtManager.createJwt(user.getUsername(), user.getRole().toString());
            //long id, String username, long profileId, Jwt token
            UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(),user.getProfile().getId(), token);
            return Response.ok(userDTO).build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
