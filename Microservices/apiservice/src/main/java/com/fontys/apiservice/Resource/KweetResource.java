package com.fontys.apiservice.Resource;


import com.fontys.apiservice.Model.DTO.KweetDTO;
import com.fontys.apiservice.Model.Kweet;
import com.fontys.apiservice.config.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@RestController
@RequestMapping(path = "/api/kweet")
public class KweetResource {

    @Autowired
    ServerProperties server;

    @GetMapping
    public List<KweetDTO> get() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(server.getHosts().get(0) + "/api/kweet/all", List.class);
    }

    @PostMapping
    public KweetDTO create(@RequestBody KweetDTO kweetDTO, @RequestHeader("Authorization") String token) {
        System.out.println(token);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token.substring(7));
        HttpEntity<KweetDTO> entity = new HttpEntity<>(kweetDTO, headers);
        return restTemplate.postForObject(server.getHosts().get(0) + "/api/kweet", entity, KweetDTO.class);
    }

    @GetMapping("/{id}/{offset}")
    public List<KweetDTO> getTimeline(@PathVariable("id") long id, @PathVariable("offset") int offset) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(server.getHosts().get(0) + "/api/kweet/" + id + "/" + offset);
        return restTemplate.getForObject(server.getHosts().get(0) + "/api/kweet/" + id + "/" + offset, List.class);
    }


}
