package DAL.MockContext;

import DAL.IContext.IUserContext;
import Enums.Role;
import Models.User;

import java.util.ArrayList;
import java.util.List;

public class MockUserContext implements IUserContext {
    List<User> users = new ArrayList<>();

    @Override
    public User registerUser(String username) {
        User user = new User(username, Role.user);
        users.add(user);
        return user;
    }

    @Override
    public User loginUser(String username) {
        for (User user : users){
            if(username.equals(user.getUsername())) return user;
        }
        return null;
    }

    @Override
    public void logoutUser() {

    }
}
