package Controllers;

import DAL.Repo.ProfileRepo;
import Models.Kweet;
import Models.Profile;

import java.util.List;

public class ProfileController {

    private ProfileRepo repo;

    public ProfileController(ProfileRepo repo) {
        this.repo = repo;
    }

    public Profile CreateProfile(){
        throw new UnsupportedOperationException();
    }
    public void followProfile(Profile profile){
        throw new UnsupportedOperationException();
    }
    public void postKweet(String content){
        throw new UnsupportedOperationException();
    }
    public void hearthKweet(String kweet){
        throw new UnsupportedOperationException();
    }
    public List<Kweet> getTimeline(Profile profile){
        throw new UnsupportedOperationException();
    }
}
