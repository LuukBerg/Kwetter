package webappKwetter.dao.MySqlContext;

import webappKwetter.model.Enums.Role;
import webappKwetter.model.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class MySQLUserContext implements DAL.IContext.IUserContext {
    private EntityManager entityManager;

    public MySQLUserContext(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User registerUser(String username) {
        User user = new User(username, Role.user);
        entityManager.persist(user);
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
