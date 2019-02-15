package DAL.Repo;

import DAL.IContext.IUserContext;
import Models.User;

public class UserRepo {
    private IUserContext context;

    public UserRepo(IUserContext context) {
        this.context = context;
    }

    public User registerUser(String username){
        return context.registerUser(username);
    }

    public User loginUser(String username){
        return context.loginUser(username);
    }
    public void logoutUser(){
        context.logoutUser();
    }
}
