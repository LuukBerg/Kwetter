package Kwetter.dao.MySqlContext;

import Kwetter.model.Models.User;
import Kwetter.dao.IContext.IUserContext;
import Kwetter.dao.Service.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@JPA
@Stateless
public class MySQLUserContext implements IUserContext {

    @PersistenceContext(unitName = "KwetterPU")
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
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public User findbyId(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteById(long id) { }
    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username").setParameter("username", username);
        if(!query.getResultList().isEmpty()){
            return (User) query.getSingleResult();
        }
        else{
            return null;
        }
    }
}
