package webappKwetter.dao.Repo;

import DAL.IContext.IUserContext;
import webappKwetter.model.Models.User;

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
