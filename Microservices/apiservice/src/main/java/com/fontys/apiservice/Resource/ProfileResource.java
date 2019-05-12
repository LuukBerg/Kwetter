package com.fontys.apiservice.Resource;

import com.fontys.apiservice.Model.DTO.KweetDTO;
import com.fontys.apiservice.config.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "api/profile")
public class ProfileResource {

    @Autowired
    private ServerProperties server;

    @GetMapping("/{id}/kweet")
    public List<KweetDTO> getKweetByProfile(@PathVariable("id") long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(server.getHosts().get(0) + "/byprofile/" + id, List.class);
    }
}
