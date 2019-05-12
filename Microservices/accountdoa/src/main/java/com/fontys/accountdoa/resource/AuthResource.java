package com.fontys.accountdoa.resource;

import com.fontys.accountdoa.Model.DTO.RegisterDTO;
import com.fontys.accountdoa.Model.DTO.UserDTO;
import com.fontys.accountdoa.Model.Details;
import com.fontys.accountdoa.Model.Profile;
import com.fontys.accountdoa.Model.User;
import com.fontys.accountdoa.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public UserDTO login(User user){
        User login = userRepository.login(user.getUsername(),user.getHashedPassword());
        return new UserDTO(login.getId(),login.getUsername(),login.getProfile().getId(), "");
    }

    @PostMapping("/register")
    public UserDTO register(RegisterDTO registerDTO){
        User user = new User(registerDTO.getUsername(),registerDTO.getEmail(),registerDTO.getPassword());
        Profile profile = new Profile(user,new Details(registerDTO.getName(),registerDTO.getBio(),registerDTO.getWeb(),registerDTO.getLocation()));
        user.setProfile(profile);
        user = userRepository.save(user);
        if(user != null){
            return UserDTO.transform(user);
        }
        return null;
    }
}
