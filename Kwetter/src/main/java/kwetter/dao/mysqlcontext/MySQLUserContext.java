package kwetter.dao.mysqlcontext;

import kwetter.model.models.Profile;
import kwetter.model.models.User;
import kwetter.dao.icontext.IUserContext;
import kwetter.service.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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
    public void update(User entity) {
        entityManager.persist(entity);
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
    public void deleteById(long id) {
        User user = entityManager.find(User.class, id);

        for (int i = user.getProfile().getFollowers().size() -1; i>=0 ; i--) {
            Profile follower = user.getProfile().getFollowers().get(i);
            user.getProfile().removeFollower(follower);
            follower.removeFollowing(user.getProfile());
            entityManager.persist(follower);
        }
        for(int i = user.getProfile().getFollowing().size() -1; i >= 0; i--){
            Profile following = user.getProfile().getFollowing().get(i);
            user.getProfile().removeFollowing(following);
            following.removeFollower(user.getProfile());
            entityManager.persist(following);
        }

        entityManager.remove(user);
    }
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

    @Override
    public List<User> getAll() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public User login(String username, byte[] hashed) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.hashedPassword = :hashed").setParameter("username", username).setParameter("hashed",hashed);
        if(!query.getResultList().isEmpty()){
            return (User) query.getSingleResult();
        }
        return null;
    }
}
