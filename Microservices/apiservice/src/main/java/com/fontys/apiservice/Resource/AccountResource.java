package com.fontys.apiservice.Resource;

import com.fontys.apiservice.Model.DTO.RegisterDTO;
import com.fontys.apiservice.Model.DTO.UserDTO;
import com.fontys.apiservice.Model.Details;
import com.fontys.apiservice.Model.Profile;
import com.fontys.apiservice.Model.User;
import com.fontys.apiservice.config.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/api/auth")
public class AccountResource {

    @Autowired
    private ServerProperties server;

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterDTO registerDTO){
        System.out.println(registerDTO);
        RestTemplate restTemplate = new RestTemplate();
        UserDTO userDTO = restTemplate.postForObject(server.getHosts().get(1) + "/api/auth/register", registerDTO, UserDTO.class);
        return userDTO;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody  UserDTO login(@RequestParam MultiValueMap paramMap) {
        String username = paramMap.get("username").toString();
        String password = paramMap.get("password").toString();
        username = username.replace("[", "");
        username = username.replace("]", "");
        password = password.replace("[", "");
        password = password.replace("]", "");
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(server.getHosts().get(1) + "/api/auth/login/" + username + "/" + password);
        return restTemplate.getForObject(server.getHosts().get(1) + "/api/auth/login/" + username+ "/" + password, UserDTO.class);

    }
}
