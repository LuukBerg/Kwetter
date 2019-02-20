package Kwetter.dao.Repo;

import Kwetter.model.Models.Profile;
import Kwetter.dao.IContext.IKweetContext;
import Kwetter.dao.IContext.IProfileContext;

import Kwetter.model.Models.Kweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class KweetService {
    @Inject @JPA
    private IKweetContext kweetContext;

    @Inject @JPA
    private IProfileContext profileContext;

    public KweetService() {
    }

    public Kweet create(Kweet kweet){
        Profile profile = profileContext.findbyId(kweet.getId());
        kweetContext.create(kweet);
        profile.addKweet(kweet);
        profileContext.update(profile);
        return kweet;
    }
    public List<Kweet> findByProfile(long id){
        return kweetContext.findByProfile(id);
    }



    public List<Kweet> getAllOrderedByDate() {
        return kweetContext.getAllOrderedByDate();
    }

    public void delete(long id, Profile profile) {
        Kweet kweet = kweetContext.findKweetById(id);
        if(kweet != null){
            if(profile.getId() == kweet.getOwner().getId()){
                kweetContext.delete(kweet);
            }
        }
        //TODO throw not foudn error
    }
}
