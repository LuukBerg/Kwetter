package DAL.IContext;

import webappKwetter.model.Models.Details;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;
import webappKwetter.model.Models.User;

import java.util.List;

public interface IProfileContext {
    List<Kweet> getTimeLine(Profile profile);
    void hearthKweet(Kweet kweet);
    void postKweet(String content);
    void followProfile(Profile profile);
    void createProfile(User owner, Details detail);
}
