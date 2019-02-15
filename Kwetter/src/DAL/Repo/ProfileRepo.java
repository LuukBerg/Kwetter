package DAL.Repo;

import DAL.IContext.IProfileContext;
import Models.Details;
import Models.Kweet;
import Models.Profile;
import Models.User;

import java.util.List;

public class ProfileRepo {

    private IProfileContext context;

    public ProfileRepo(IProfileContext context) {
        this.context = context;
    }

    public List<Kweet> getTimeLine(Profile profile){
        return context.getTimeLine(profile);
    }
    void hearthKweet(Kweet kweet){
        context.hearthKweet(kweet);
    }
    void postKweet(String content){
        context.postKweet(content);
    }
    void followProfile(Profile profile){
        context.followProfile(profile);
    }
    void createProfile(User owner, Details detail){
        context.createProfile(owner,detail);
    }
}
