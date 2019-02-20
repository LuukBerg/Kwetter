package Kwetter.dao;


import Kwetter.model.Models.Details;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.User;
import Kwetter.dao.IContext.IProfileContext;
import Kwetter.dao.IContext.IUserContext;
import Kwetter.dao.Repo.ProfileService;
import Kwetter.dao.Repo.UserService;
import Kwetter.model.Models.Kweet;


import java.util.List;

public class KwetterFacade {

    private User user;
    UserService userRepo;
    ProfileService profileRepo;

    public User getUser(){
        return user;
    }
    public KwetterFacade(IUserContext userContext, IProfileContext profileContext) {
        userRepo = new UserService();
        profileRepo = new ProfileService();
    }

    public User registerUser(String username){
        throw new UnsupportedOperationException();
    }
    public User loginUser(String username){
        throw new UnsupportedOperationException();
    }
    public void logoutUser(){
        throw new UnsupportedOperationException();
    }
    public List<Kweet> getTimeLine(Profile profile){
        throw new UnsupportedOperationException();
    }
    public void hearthKweet(Kweet kweet){
        throw new UnsupportedOperationException();
    }
    public void postKweet(String content){
        throw new UnsupportedOperationException();
    }
    public void followProfile(Profile profile){
        throw new UnsupportedOperationException();
    }
    public void createProfile(User owner, Details detail){
        throw new UnsupportedOperationException();
    }
    public Profile getProfile(String username){
        throw new UnsupportedOperationException();
    }
    public void setDetails(Details details){
        throw new UnsupportedOperationException();
    }

}
