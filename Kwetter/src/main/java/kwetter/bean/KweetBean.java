package kwetter.bean;

import kwetter.model.models.Kweet;
import kwetter.service.KweetService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "kweetBean")
@SessionScoped
public class KweetBean implements Serializable {

    @Inject
    private KweetService kweetService;

    public List<Kweet> getKweets(){ return kweetService.getAllOrderedByDate();}

    public void deleteKweet(long id){kweetService.delete(id);}
}
