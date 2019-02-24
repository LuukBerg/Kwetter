package kwetter.dao.service;

import kwetter.model.commands.Hearth;
import kwetter.model.models.Profile;
import kwetter.dao.icontext.IKweetContext;
import kwetter.dao.icontext.IProfileContext;

import kwetter.model.models.Kweet;

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
    public KweetService(IKweetContext kweetContext, IProfileContext profileContext){
        this.kweetContext = kweetContext;
        this.profileContext = profileContext;
    }

    public Kweet create(Kweet kweet){
        Profile profile = profileContext.findbyId(kweet.getOwner().getId());
        kweetContext.create(kweet);
        profile.addKweet(kweet);

        //TODO check
        profileContext.update(profile);
        return kweet;
    }
    public List<Kweet> findByProfile(long id){
        return kweetContext.findByProfile(id);
    }



    public List<Kweet> getAllOrderedByDate() {
        return kweetContext.getAllOrderedByDate();
    }

    public void delete(long id) {
        Kweet kweet = kweetContext.findKweetById(id);
        if(kweet != null){

                kweetContext.deleteById(id);

        }
        //TODO throw not foudn error
    }
    public Kweet addHearth(Kweet kweet, Hearth hearth){
        kweet.addHearht(hearth);
        return kweetContext.update(kweet);

    }
    public Kweet findById(long id){
        return kweetContext.findKweetById(id);
    }
}
