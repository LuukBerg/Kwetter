package kwetter.controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("/api")
public class JAXRSApplication extends Application {

  /*  @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>> resources = new java.util.HashSet<>();


        // Filters (Auth)
        resources.add(SecurityFilter.Authorizer.class);
        resources.add(SecurityFilter.class);


        return resources;

    }*/
}
