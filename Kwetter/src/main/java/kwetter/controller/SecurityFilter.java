package kwetter.controller;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import kwetter.JWT.JwtManager;
import kwetter.model.enums.Role;
import kwetter.model.models.User;
import net.minidev.json.JSONArray;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;
import java.security.Principal;
import java.security.Security;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Provider
@SecureAuth
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter  implements ContainerRequestFilter {
    public static final String BEARER = "Bearer";


    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String auth = containerRequestContext.getHeaderString(AUTHORIZATION);
        if(auth != null && auth.startsWith(BEARER)){
            try{
                JWT jwt = JWTParser.parse(auth.substring(7));

                System.out.println("_-----------------------------------------");
                System.out.println(jwt.getJWTClaimsSet().getClaims());
                String username = jwt.getJWTClaimsSet().getClaims().get("sub").toString();
                JSONArray groups = (JSONArray) jwt.getJWTClaimsSet().getClaims().get("groups");
                System.out.println("uname: "+ username);
                System.out.println("groups: " + groups.get(0).toString());

                final SecurityContext securityContext = containerRequestContext.getSecurityContext();
                Authorizer authorizer = new Authorizer(groups.get(0).toString(),username,securityContext.isSecure());
                containerRequestContext.setSecurityContext(authorizer);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }


    @Provider
    public static class Authorizer implements SecurityContext{

        private String role;
        private String username;
        private boolean isSecure;

        public Authorizer(String role, String username, boolean isSecure) {
            this.role = role;
            this.username = username;
            this.isSecure = isSecure;
        }

        @Override
        public Principal getUserPrincipal() {
            Principal p = new Principal() {
                @Override
                public String getName() {
                    return username;
                }
            };
            return p;
        }

        @Override
        public boolean isUserInRole(String role) {
            return this.role.equals(role);
        }

        @Override
        public boolean isSecure() {
            return isSecure;
        }

        @Override
        public String getAuthenticationScheme() {
            return "Your Scheme";
        }
    }

}
