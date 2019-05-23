package com.fontys.profiledoa.resource;

import com.fontys.profiledoa.model.DTO.ProfileDTO;
import com.fontys.profiledoa.model.Profile;
import com.fontys.profiledoa.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/api/profile")
public class ProfileResource {

    @Autowired
    ProfileRepository profileRepository;

    @GetMapping(path = "/{id}")
    public Profile getProfileByUserName(@PathVariable("id") long id){
        System.out.println(id);
        Profile profile = profileRepository.findById(id).get();
        System.out.println(profile);
        return profile;
    }
}
