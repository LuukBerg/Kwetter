package Kwetter.dao.IContext;

import Kwetter.model.Models.Profile;

public interface IProfileContext {
    //List<Kweet> getTimeLine(Profile profile);
    //void hearthKweet(Kweet kweet);
    //void postKweet(String content);
    //void followProfile(Profile profile);
    //void createProfile(User owner, Details detail);
    Profile update(Profile entity);
    Profile create(Profile entity);
    Profile findbyId(long id);
    void deleteById(long id);
}
