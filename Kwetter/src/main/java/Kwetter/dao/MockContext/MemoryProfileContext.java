package Kwetter.dao.MockContext;

import Kwetter.model.Models.Profile;
import Kwetter.dao.IContext.IProfileContext;
import Kwetter.dao.Service.Mock;

import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Default
@Stateful
@Mock
public class MemoryProfileContext implements IProfileContext {
    private List<Profile> profiles = new ArrayList<Profile>();
    private long idIncrement = 0;
    @Override
    public Profile update(Profile entity) {
        for (Profile profile : profiles){
            if(profile.getId() == entity.getId()){
                profiles.remove(profile);
                profiles.add(entity);
                return profile;
            }
        }
        return null;
    }

    @Override
    public Profile create(Profile entity) {
        entity.setId(idIncrement++);
        profiles.add(entity);
        return entity;
    }

    @Override
    public Profile findbyId(long id) {
        for (Profile profile : profiles){
            if(profile.getId() == id){
                return profile;
            }
        }
        return null;

    }

    @Override
    public void deleteById(long id) {
        Profile profileToRemove = null;
        for (Profile profile : profiles){
            if(profile.getId() == id){
                profileToRemove = profile;
            }
        }
        profiles.remove(profileToRemove);
    }
}
