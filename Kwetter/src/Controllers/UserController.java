package Controllers;

import DAL.Repo.UserRepo;
import Models.Kweet;
import Models.Profile;
import Models.User;

import java.util.List;

public class UserController {
    private UserRepo repo;

    public UserController(UserRepo repo) {
        this.repo = repo;
    }

    public User login(String username){
        throw new UnsupportedOperationException();
    }
    public User Register(String username){
        throw new UnsupportedOperationException();

    }
    public void Logout(){
        throw new UnsupportedOperationException();
    }
}
