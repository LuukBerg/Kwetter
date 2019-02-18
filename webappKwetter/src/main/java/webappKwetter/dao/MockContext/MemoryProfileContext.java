package webappKwetter.dao.MockContext;

import org.hibernate.annotations.ColumnDefault;
import webappKwetter.dao.IContext.IProfileContext;

import webappKwetter.dao.Repo.Mock;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;


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
        for (Profile profile : profiles){
            if(profile.getId() == id){
                profiles.remove(profile);
            }
        }
    }
}
