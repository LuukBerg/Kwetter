package kwetter.service;

import kwetter.model.commands.Hearth;
import kwetter.dao.icontext.IKweetContext;
import kwetter.dao.icontext.IProfileContext;
import kwetter.model.models.Kweet;
import kwetter.model.models.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class KweetService implements Serializable {
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

    public List<Kweet> getTimeline(long profileId, int offset){
        return kweetContext.getTimeLine(profileId,offset);
    }

    public Kweet create(Kweet kweet){
        System.out.println("in service");
        kweetContext.create(kweet);
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
