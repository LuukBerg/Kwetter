package webappKwetter.dao.IContext;

import webappKwetter.model.Models.User;

public interface IUserContext {
    User registerUser(User user);
    User loginUser(String username);
    void logoutUser();

}
