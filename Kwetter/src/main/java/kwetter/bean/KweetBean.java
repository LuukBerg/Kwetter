package kwetter.bean;

import kwetter.model.models.Kweet;
import kwetter.service.KweetService;

import javax.annotation.ManagedBean;
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
        List<Kweet> kweets = kweetService.getAllOrderedByDate();
        return kweets;
    }

    public void deleteKweet(long id) throws Exception{
        System.out.println(id + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        kweetService.delete(id);
    }
    public void Test(){
        System.out.println("****************************************");
    }
}
