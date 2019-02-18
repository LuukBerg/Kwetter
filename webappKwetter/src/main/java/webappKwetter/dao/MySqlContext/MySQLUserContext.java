package webappKwetter.dao.MySqlContext;

import webappKwetter.dao.IContext.IUserContext;
import webappKwetter.dao.Repo.JPA;
import webappKwetter.model.Enums.Role;
import webappKwetter.model.Models.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@JPA
public class MySQLUserContext implements IUserContext {
    @Inject @MySQLDatabase
    private EntityManager entityManager;

    public MySQLUserContext(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public MySQLUserContext() {
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public User findbyId(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
