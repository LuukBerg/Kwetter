package webappKwetter.dao.MySqlContext;

import webappKwetter.dao.IContext.IUserContext;
import webappKwetter.model.Enums.Role;
import webappKwetter.model.Models.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class MySQLUserContext implements IUserContext {
    @Inject @MySQLDatabase
    private EntityManager entityManager;

    public MySQLUserContext(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public MySQLUserContext() {
    }

    @Override
    public User registerUser(User user) {

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
