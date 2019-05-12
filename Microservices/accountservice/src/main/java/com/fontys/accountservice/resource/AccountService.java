package com.fontys.accountservice.resource;

import com.fontys.accountservice.jwt.JwtManager;
import com.fontys.accountservice.model.DTO.UserDTO;
import com.fontys.accountservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "/api/auth/login")
public class AccountService {

    @Autowired
    private JwtManager jwtManager;

    //TODO security?
    @GetMapping("/login/{username}/{password}")
    public UserDTO login(@PathVariable("username") String username, @PathVariable("password") String password) {
        byte[] hashed = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hashed = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //todo call to doa
        User user = new User(username, password);
        String token = jwtManager.createJwt()
        return null;

    }

    @PostMapping("/register")
    public UserDTO register(User user) {
        String unhashed = user.getPassword();
        byte[] hashed = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hashed = digest.digest(
                    unhashed.getBytes(StandardCharsets.UTF_8));
            user.setHashedPassword(hashed);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //todo doa call
        /*if(context.findByUsername(user.getUsername()) == null){
            return context.create(user);
        }*/
        return null;

    }
}
