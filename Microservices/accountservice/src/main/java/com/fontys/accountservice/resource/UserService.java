package com.fontys.accountservice.resource;

import com.fontys.accountservice.config.ServerProperties;
import com.fontys.accountservice.model.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserService {

    @Autowired
    ServerProperties server;

    @GetMapping(path = "/search/{query}")
    public List<UserDTO> search(@PathVariable("query") String query){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(server.getHosts().get(0) + "/api/user/search/" + query, List.class);
    }
}
