package com.fontys.accountservice.resource;

import com.fontys.accountservice.config.ServerProperties;
import com.fontys.accountservice.jwt.JwtManager;
import com.fontys.accountservice.model.DTO.RegisterDTO;
import com.fontys.accountservice.model.DTO.UserDTO;
import com.fontys.accountservice.model.User;
import com.fontys.accountservice.model.enums.Role;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "/api/auth")
public class AccountService {

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    ServerProperties server;


    //TODO security?
    @GetMapping("/login/{username}/{password}")
    public UserDTO login(@PathVariable("username") String username, @PathVariable("password") String password) throws Exception {
        String hash = Hashpassword(password);
        //todo call to doa
        RestTemplate restTemplate = new RestTemplate();
        User userPost = new User(username, null, password);
        userPost.setHashedPassword(hash);
        //String username, String email, String password)
        UserDTO user = restTemplate.getForObject(server.getHosts().get(0) + "/api/auth/login/" + username + "/" + hash, UserDTO.class);
        String token = jwtManager.createJwt(user.getUsername(), Role.USER.toString());
        user.setToken(token);
        System.out.println(user);
        return user;

    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterDTO registerDTO) {
        String unhashed = registerDTO.getPassword();

        registerDTO.setHashed(Hashpassword(unhashed));
        RestTemplate restTemplate = new RestTemplate();
        UserDTO userFound = restTemplate.getForObject(server.getHosts().get(0) + "/api/user/username/" + registerDTO.getUsername(), UserDTO.class);
        if(userFound == null ){
            return restTemplate.postForObject(server.getHosts().get(0) + "/api/auth/register", registerDTO,UserDTO.class);
        }
        return null;

    }

    private static String Hashpassword(String password) {
        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
