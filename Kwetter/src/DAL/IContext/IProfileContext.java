package DAL.IContext;

import Models.Details;
import Models.Kweet;
import Models.Profile;
import Models.User;

import java.util.List;

public interface IProfileContext {
    List<Kweet> getTimeLine(Profile profile);
    void hearthKweet(Kweet kweet);
    void postKweet(String content);
    void followProfile(Profile profile);
    void createProfile(User owner, Details detail);
}
