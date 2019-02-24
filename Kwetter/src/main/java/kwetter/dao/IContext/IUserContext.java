package Kwetter.dao.IContext;

import Kwetter.model.Models.User;

public interface IUserContext {
    User update(User entity);
    User create(User entity);
    User findbyId(long id);
    void deleteById(long id);
    User findByUsername(String username);
}
