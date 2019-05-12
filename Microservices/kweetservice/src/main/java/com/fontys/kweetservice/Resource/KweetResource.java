package com.fontys.kweetservice.Resource;

import com.fontys.kweetservice.Model.DTO.KweetDTO;
import com.fontys.kweetservice.config.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path="/api/kweet")
public class KweetResource {

    @Autowired
    private ServerProperties server;

    @GetMapping("/{id}/{offset}")
    public List<KweetDTO> getTimeline(@PathVariable("id") long profileId, @PathVariable("offset") int offset){
        RestTemplate restTemplate = new RestTemplate();
        List<KweetDTO> dtos = restTemplate.getForObject(server.getHosts().get(0) + "/api/kweet/" + profileId + "/" + offset, List.class);
        return dtos;
    }

    @PostMapping
    public KweetDTO create(KweetDTO kweetDTO){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(server.getHosts().get(0), kweetDTO);
        return kweetDTO;
    }
    @GetMapping("/byprofile/{id}")
    public List<KweetDTO> findByProfile(@PathVariable("id") long id){
        RestTemplate restTemplate = new RestTemplate();
        List<KweetDTO> dtos = restTemplate.getForObject(server.getHosts().get(0) + "/api/kweet/byprofile/" + id, List.class);
        return dtos;
    }



    @GetMapping("/all")
    public List<KweetDTO> getAllOrderedByDate() {
        RestTemplate restTemplate = new RestTemplate();
        List<KweetDTO> dtos = restTemplate.getForObject(server.getHosts().get(0) + "/api/kweet/all" ,List.class);
        return dtos;
    }

    @GetMapping(path = "/{id}")
    public KweetDTO findById(@PathVariable("id") long id){
        RestTemplate restTemplate = new RestTemplate();
        KweetDTO dto = restTemplate.getForObject(server.getHosts().get(0) + "/api/kweet/" + id ,KweetDTO.class);
        return dto;
    }

}
