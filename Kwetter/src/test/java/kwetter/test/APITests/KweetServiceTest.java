package kwetter.test.APITests;

import kwetter.dao.icontext.IKweetContext;
import kwetter.dao.mysqlcontext.MySQLKweetContext;
import kwetter.dao.mysqlcontext.MySQLProfileContext;
import kwetter.model.enums.Role;
import kwetter.model.models.Details;
import kwetter.model.models.Kweet;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
import kwetter.service.KweetService;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class KweetServiceTest {

    private static String baseUrl = "http://localhost:8080/Kwetter/api";
    @Test
    public void createUserTest(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        WebTarget target = client.target(baseUrl + "/USER/?username=testuser");
        Invocation.Builder builder = target.request();
        Response response = builder.post(Entity.json(""));
        System.out.println(response.toString());
        Assert.assertEquals(200, response.getStatus());
    }
    @Test
    //@InSequence(4)
    public void findUserById(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        WebTarget target = client.target(baseUrl + "/USER/id/?id=1");
        Response response = target.request().get();
        System.out.println(response.getEntity());
        System.out.println(response.readEntity(String.class));
        Assert.assertEquals(200, response.getStatus());
    }
}