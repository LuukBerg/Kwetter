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

    public List<Kweet> getKweets(){
        System.out.println("---------------------------------------------------------------------------");
        List<Kweet> keets = kweetService.getAllOrderedByDate();
        keets.get(0).getOwner();
        return keets;
    }

    public void deleteKweet() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        kweetService.delete(5);
    }
    public void Test(){
        System.out.println("****************************************");
    }
}
