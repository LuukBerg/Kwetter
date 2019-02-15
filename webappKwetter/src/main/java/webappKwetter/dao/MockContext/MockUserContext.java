package webappKwetter.dao.MockContext;


import webappKwetter.dao.IContext.IUserContext;
import webappKwetter.model.Models.User;
import webappKwetter.model.Enums.Role;

import java.util.ArrayList;
import java.util.List;

public class MockUserContext implements IUserContext {
    List<User> users = new ArrayList<>();


    @Override
    public User registerUser(User user) {
        for (User user1 : users){
            if(user.getUsername().equals(user1.getUsername())) return null;
        }
        return user;
    }

    @Override
    public User loginUser(String username) {
        return null;
    }

    @Override
    public void logoutUser() {

    }
}
