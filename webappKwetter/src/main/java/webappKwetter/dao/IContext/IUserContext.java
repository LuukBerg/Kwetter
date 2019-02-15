package DAL.IContext;

import webappKwetter.model.Models.User;

public interface IUserContext {
    User registerUser(String username);
    User loginUser(String username);
    void logoutUser();

}
