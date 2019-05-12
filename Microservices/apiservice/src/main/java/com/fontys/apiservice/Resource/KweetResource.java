package com.fontys.apiservice.Resource;


import com.fontys.apiservice.Model.DTO.KweetDTO;
import com.fontys.apiservice.config.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "/api/kweet")
public class KweetResource {

    @Autowired
    ServerProperties server;

    @GetMapping
    public List<KweetDTO> get() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(server.getHosts().get(0) + "api/kweet/all", List.class);
    }

    @PostMapping
    public KweetDTO create(KweetDTO kweetDTO) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(server.getHosts().get(0), kweetDTO, KweetDTO.class);
    }

    @GetMapping("/{id}/{offset}")
    public List<KweetDTO> getTimeline(@PathVariable("id") long id, @PathVariable("offset") int offset) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(server.getHosts().get(0) + "/" + id + "/" + offset, List.class);
    }


}
