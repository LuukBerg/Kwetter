package kwetter.test.APITests;


import kwetter.model.DTO.KweetDTO;
import kwetter.model.DTO.RegisterDTO;
import kwetter.model.models.Kweet;
import kwetter.model.models.Profile;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import java.util.List;

public class APISearchTest {

    private static String baseUrl = "http://localhost:18080/Kwetter/api";
    @Test
    public void createSearchEnv() throws ParseException {


        for(int i = 0; i < 20 ;i++) {
            ResteasyClient client = new ResteasyClientBuilder().build();
            WebTarget target = client.target(baseUrl + "/user");
            Invocation.Builder builder = target.request();
            RegisterDTO dto = new RegisterDTO("usernameSearch" + i, "email", "password", "test", "test", "test", "test");
            Entity json = Entity.json(dto);
            Response response = builder.post(json);
            Assert.assertEquals(200, response.getStatus());
        }
    }
}