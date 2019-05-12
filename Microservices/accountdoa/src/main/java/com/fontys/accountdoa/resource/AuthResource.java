package com.fontys.accountdoa.resource;

import com.fontys.accountdoa.Model.DTO.RegisterDTO;
import com.fontys.accountdoa.Model.DTO.UserDTO;
import com.fontys.accountdoa.Model.Details;
import com.fontys.accountdoa.Model.Profile;
import com.fontys.accountdoa.Model.User;
import com.fontys.accountdoa.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login/{username}/{password}")
    public UserDTO login(@PathVariable("username") String username, @PathVariable("password") String hashed){
        System.out.println(username+ " p: " + hashed);
        User login = userRepository.login(username,hashed);
        System.out.println(login);
        return new UserDTO(login.getId(),login.getUsername(),login.getProfile().getId(), "");
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterDTO registerDTO){
        User user = new User(registerDTO.getUsername(),registerDTO.getEmail(),registerDTO.getPassword());
        Profile profile = new Profile(user,new Details(registerDTO.getName(),registerDTO.getBio(),registerDTO.getWeb(),registerDTO.getLocation()));
        user.setProfile(profile);
        user.setHashedPassword(registerDTO.getHashed());
        user = userRepository.save(user);
        if(user != null){
            return UserDTO.transform(user);
        }
        return null;
    }
}
