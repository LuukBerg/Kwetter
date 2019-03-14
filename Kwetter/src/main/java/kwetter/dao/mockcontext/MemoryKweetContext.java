package kwetter.dao.mockcontext;

import kwetter.dao.icontext.IKweetContext;
import kwetter.service.Mock;
import kwetter.model.models.Profile;
import kwetter.model.models.Kweet;

import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Default
@Stateful
@Mock
public class MemoryKweetContext implements IKweetContext {
    private List<Kweet> kweets = new ArrayList<>();
    private long idIncrement=1;

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
    public List<Kweet> getTimeLine(Profile profile, int offset) {
        List<Kweet> result = new ArrayList<>();
        for(Kweet kweet : kweets){
            if(result.size() != 10){
                if(kweet.getOwner().getId() == profile.getId())result.add(kweet);
            }
        }
        return result;
    }

    @Override
    public List<Kweet> getAllOrderedByDate() {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
        Kweet kweetToRemove = null;
        for(Kweet kweetItem : kweets){
            if(kweetItem.getId() == id){
                kweetToRemove = kweetItem;
            }
        }
        kweets.remove(kweetToRemove);
    }


    @Override
    public Kweet update(Kweet kweet) {
        if(kweets.contains(kweet)){
            for(Kweet kweetItem : kweets){
                if(kweetItem.getId() == kweet.getId()){
                    kweets.remove(kweetItem);
                    kweets.add(kweet);
                }
            }
        }
        return kweet;
    }
}
