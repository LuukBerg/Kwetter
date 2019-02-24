package kwetter.dao.icontext;

import kwetter.model.models.User;

public interface IUserContext {
    User update(User entity);
    User create(User entity);
    User findbyId(long id);
    void deleteById(long id);
    User findByUsername(String username);
}
