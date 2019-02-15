package Facade;

import DAL.IContext.IProfileContext;
import DAL.IContext.IUserContext;
import DAL.Repo.ProfileRepo;
import DAL.Repo.UserRepo;
import webappKwetter.model.Models.Details;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;
import webappKwetter.model.Models.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class KwetterFacade {

    private User user;
    UserRepo userRepo;
    ProfileRepo profileRepo;

    public User getUser(){
        return user;
    }
    public KwetterFacade(IUserContext userContext, IProfileContext profileContext) {
        userRepo = new UserRepo(userContext);
        profileRepo = new ProfileRepo(profileContext);
    }

    public User registerUser(String username){
        throw new NotImplementedException();
    }
    public User loginUser(String username){
        throw new NotImplementedException();
    }
    public void logoutUser(){
        throw new NotImplementedException();
    }
    public List<Kweet> getTimeLine(Profile profile){
        throw new NotImplementedException();
    }
    public void hearthKweet(Kweet kweet){
        throw new NotImplementedException();
    }
    public void postKweet(String content){
        throw new NotImplementedException();
    }
    public void followProfile(Profile profile){
        throw new NotImplementedException();
    }
    public void createProfile(User owner, Details detail){
        throw new NotImplementedException();
    }
    public Profile getProfile(String username){
        throw new NotImplementedException();
    }
    public void setDetails(Details details){
        throw new NotImplementedException();
    }

}
