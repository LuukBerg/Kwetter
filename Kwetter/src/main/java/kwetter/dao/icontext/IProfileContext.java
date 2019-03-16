package kwetter.dao.icontext;

import kwetter.model.models.Profile;

import java.util.List;


public interface IProfileContext {
    Profile update(Profile entity);
    Profile create(Profile entity);
    Profile findbyId(long id);
    void deleteById(long id);
    List<Profile> getFollowing(long id);
    List<Profile> getFollowers(long id);
}

