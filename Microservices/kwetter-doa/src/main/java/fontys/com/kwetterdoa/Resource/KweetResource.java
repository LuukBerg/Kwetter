package fontys.com.kwetterdoa.Resource;

import fontys.com.kwetterdoa.Repository.KweetRepository;
import fontys.com.kwetterdoa.Model.DTO.KweetDTO;
import fontys.com.kwetterdoa.Model.Kweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController   // This means that this class is a Controller
@RequestMapping(path="/api/kweet") // This means URL's start with /demo (after Application path)
public class KweetResource {

    @Autowired
    KweetRepository kweetRepository;


    @GetMapping(path = "/{id}")
    public KweetDTO getKweet(@PathVariable("id") long id){
        Kweet kweet = kweetRepository.findById(id).get();
        return KweetDTO.transform(kweet);
    }
    @GetMapping(path = "/all")
    public List<KweetDTO> getAll(){
        return KweetDTO.transform(kweetRepository.findAll());
    }
    @PostMapping()
    public void createKweet(@RequestBody final KweetDTO kweetdto){
        //Kweet kweet = new Kweet(kweetdto.getContent(), kweetdto.getOwnerId());
        //kweetRepository.save()
    }
    @GetMapping(path = "/{id}/{offset}")
    public List<KweetDTO> getTimeline(@PathVariable("id") long id, @PathVariable("offset") int offset){
        return KweetDTO.transform(kweetRepository.getTimeline(id));
    }



}
