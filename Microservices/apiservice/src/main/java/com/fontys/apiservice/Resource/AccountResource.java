package com.fontys.apiservice.Resource;

import com.fontys.apiservice.Model.DTO.RegisterDTO;
import com.fontys.apiservice.Model.DTO.UserDTO;
import com.fontys.apiservice.config.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/api/auth")
public class AccountResource {

    @Autowired
    private ServerProperties server;

    @PostMapping("/register")
    public UserDTO register(RegisterDTO registerDTO) {

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public UserDTO login(MultiValueMap paramMap) {
        String username = (String) paramMap.get("username");
        String password = (String) paramMap.get("password");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(server.getHosts().get(1) + "/api/auth/login/" + username"/" + password, UserDTO.class);

    }
}
