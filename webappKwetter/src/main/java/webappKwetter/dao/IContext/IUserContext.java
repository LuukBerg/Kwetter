package webappKwetter.dao.IContext;

import webappKwetter.model.Models.Profile;
import webappKwetter.model.Models.User;

public interface IUserContext {
    User update(User entity);
    User create(User entity);
    User findbyId(long id);
    void deleteById(long id);
    User findByUsername(String username);
}
