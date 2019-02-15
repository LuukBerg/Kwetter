package webappKwetter.dao.Repo;

import DAL.IContext.IProfileContext;
import webappKwetter.model.Models.Details;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;
import webappKwetter.model.Models.User;

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
