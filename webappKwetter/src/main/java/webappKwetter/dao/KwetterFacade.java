package webappKwetter.dao;


import webappKwetter.dao.IContext.IProfileContext;
import webappKwetter.dao.IContext.IUserContext;
import webappKwetter.dao.Repo.ProfileRepo;
import webappKwetter.dao.Repo.UserRepo;
import webappKwetter.model.Models.Details;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;
import webappKwetter.model.Models.User;


import java.util.List;

public class KwetterFacade {

    private User user;
    UserRepo userRepo;
    ProfileRepo profileRepo;

    public User getUser(){
        return user;
    }
    public KwetterFacade(IUserContext userContext, IProfileContext profileContext) {
        userRepo = new UserRepo();
        profileRepo = new ProfileRepo();
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
