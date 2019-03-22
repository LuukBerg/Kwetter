package kwetter.dao.icontext;

import kwetter.model.models.User;

import java.util.List;

public interface IUserContext {
    void update(User entity);
    User create(User entity);
    User findbyId(long id);
    void deleteById(long id);
    User findByUsername(String username);

    List<User> getAll();
}
