package Kwetter.dao.MockContext;

import Kwetter.dao.IContext.IKweetContext;
import Kwetter.dao.Repo.Mock;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.Kweet;

import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Default
@Stateful
@Mock
public class MemoryKweetContext implements IKweetContext {
    private List<Kweet> kweets = new ArrayList<>();
    private long idIncrement=0;

    @Override
    public Kweet create(Kweet kweet) {
            kweet.setId(idIncrement);
            kweets.add(kweet);
            return kweet;
    }

    @Override
    public Kweet findKweetById(long id) {
        for(Kweet kweet : kweets){
            if(kweet.getId() == id) return kweet;
        }
        return null;
    }

    @Override
    public List<Kweet> findByProfile(long id) {
        List<Kweet> result = new ArrayList<>();
        for(Kweet kweet : kweets){
            if(kweet.getOwner().getId() == id)result.add(kweet);
        }
        return result;
    }

    @Override
    public List<Kweet> getTimeLine(Profile profile) {
        List<Kweet> result = new ArrayList<>();
        for(Kweet kweet : kweets){
            if(kweet.getOwner().getId() == profile.getId())result.add(kweet);
        }
        //TODO add sort on date
        //KweetComparator comp = new KweetComparator();
        //result.sort(comp);
        return result;
    }

    @Override
    public List<Kweet> getAllOrderedByDate() {
        return null;
    }

    @Override
    public void delete(Kweet kweet) {

    }
}
