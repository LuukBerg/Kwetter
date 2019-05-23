package fontys.com.kwetterdoa.Resource;

import fontys.com.kwetterdoa.Model.DTO.KweetDTO;
import fontys.com.kwetterdoa.Model.DTO.UserDTO;
import fontys.com.kwetterdoa.Model.Kweet;
import fontys.com.kwetterdoa.Model.Profile;
import fontys.com.kwetterdoa.Repository.KweetRepository;
import fontys.com.kwetterdoa.config.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController   // This means that this class is a Controller
@RequestMapping(path = "/api/kweet") // This means URL's start with /demo (after Application path)
public class KweetResource {

    @Autowired
    KweetRepository kweetRepository;

    @Autowired
    private ServerProperties server;

    @GetMapping(path = "/{id}")
    public KweetDTO getKweet(@PathVariable("id") long id) {
        Kweet kweet = kweetRepository.findById(id).get();
        return KweetDTO.transform(kweet);
    }

    @GetMapping(path = "/all")
    public List<KweetDTO> getAll() {
        return KweetDTO.transform(kweetRepository.findAll());
    }

    @PostMapping
    public void createKweet(@RequestBody final KweetDTO kweetdto) {
        System.out.println(kweetdto);
        RestTemplate restTemplate = new RestTemplate();

        UserDTO userDTO = restTemplate.getForObject(server.getHosts().get(0) + "/api/user/username/" + kweetdto.getOwner(), UserDTO.class);
        System.out.println(userDTO.getProfileId());
        Profile profile = restTemplate.getForObject(server.getHosts().get(1) + "/api/profile/" + userDTO.getProfileId(), Profile.class);
        System.out.println(profile);
        Kweet kweet = new Kweet(kweetdto.getContent(), profile);
        kweetRepository.save(kweet);
    }

    @GetMapping(path = "/{id}/{offset}")
    public List<KweetDTO> getTimeline(@PathVariable("id") long id, @PathVariable("offset") int offset) {
        return KweetDTO.transform(kweetRepository.getTimeline(id));
    }

    @GetMapping(path = "/byprofile/{id}")
    public List<KweetDTO> getByProfile(@PathVariable("id") long id) {
        return KweetDTO.transform(kweetRepository.findbyProfileId(id));
    }


}
