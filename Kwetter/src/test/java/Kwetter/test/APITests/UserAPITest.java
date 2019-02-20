package Kwetter.test.APITests;

import Kwetter.Controller.UserController;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import javax.ws.rs.client.*;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URL;

@RunWith(Arquillian.class)
public class UserAPITest {
    private static String baseUrl = "http://localhost:8080/Kwetter/api";


    @Deployment(testable = false)
    public static WebArchive createDeployment(){
        File[] files = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importRuntimeDependencies()
                .resolve()
                .withTransitivity()
                .asFile();
        return ShrinkWrap.create(WebArchive.class, "Kwetter.war")
                .addPackage("Kwetter")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                .addAsLibraries(files);
    }

    @Test @RunAsClient
    @InSequence(1)
    public void createUserTest(@ArquillianResource URL base){
        ResteasyClient client = new ResteasyClientBuilder().build();
        WebTarget target = client.target(base.toString() + "api/user/?username=testuser");
        Invocation.Builder builder = target.request();
        Response response = builder.post(Entity.json(""));
        Assert.assertEquals(200, response.getStatus());
    }
    @Test
    @InSequence(2)
    public void createUserTest2(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        WebTarget target = client.target(baseUrl + "/user/?username=testuser2");
        Invocation.Builder builder = target.request();
        Response response = builder.post(Entity.json(""));
        Assert.assertEquals(200, response.getStatus());
    }
    @Test
    @InSequence(3)
    public void findUserByUsername(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        WebTarget target = client.target(baseUrl + "/user/username/?username=testuser");
        Response response = target.request().get();
        System.out.println(response.getEntity());
        System.out.println(response.readEntity(String.class));
        Assert.assertEquals(200, response.getStatus());
    }
    @Test
    @InSequence(4)
    public void findUserById(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        WebTarget target = client.target(baseUrl + "/user/id/?id=1");
        Response response = target.request().get();
        System.out.println(response.getEntity());
        System.out.println(response.readEntity(String.class));
        Assert.assertEquals(200, response.getStatus());
    }
    @Test
    @InSequence(5)
    public void updateRole(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        WebTarget target = client.target(baseUrl + "/user/1?role=mod");
        Invocation.Builder builder = target.request();
        Response response = builder.put(Entity.json(""));
        Assert.assertEquals(204, response.getStatus());

        target = client.target(baseUrl + "/user/username/?username=testuser");
        response = target.request().get();
        System.out.println(response.getEntity());
        System.out.println(response.readEntity(String.class));
        Assert.assertEquals(200, response.getStatus());
    }
}
