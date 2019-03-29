package kwetter.bean;

import kwetter.model.models.Kweet;
import kwetter.service.KweetService;

import javax.annotation.ManagedBean;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "kweetBean")
@SessionScoped
@DeclareRoles("MOD")
public class KweetBean implements Serializable {

    @Inject
    private KweetService kweetService;

    @RolesAllowed("MOD")
    public List<Kweet> getKweets(){
        List<Kweet> kweets = kweetService.getAllOrderedByDate();
        return kweets;
    }

    @RolesAllowed("MOD")
    public void deleteKweet(long id){
        kweetService.delete(id);
    }
}
