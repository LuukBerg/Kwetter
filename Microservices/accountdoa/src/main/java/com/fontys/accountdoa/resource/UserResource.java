package com.fontys.accountdoa.resource;

import com.fontys.accountdoa.Model.DTO.RegisterDTO;
import com.fontys.accountdoa.Model.DTO.UserDTO;
import com.fontys.accountdoa.Model.Details;
import com.fontys.accountdoa.Model.Profile;
import com.fontys.accountdoa.Model.User;
import com.fontys.accountdoa.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserResource {
    @Autowired
    UserRepository userRepository;


    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") long id){
        User user = userRepository.findById(id).get();
        return UserDTO.transform(user);
    }
    @GetMapping("/username/{username}")
    public UserDTO getUserByUsername(@PathVariable("username") String username){
        User user =userRepository.findByUsername(username);
        if(user != null){
            return UserDTO.transform(user);
        }
        return null;
    }
    @GetMapping(path = "/search/{query}")
    public List<UserDTO> search(@PathVariable("query") String query){
        return UserDTO.transform(userRepository.findPartialUsername(query));
    }
}
