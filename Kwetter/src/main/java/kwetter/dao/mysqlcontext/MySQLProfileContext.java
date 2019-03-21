package kwetter.dao.mysqlcontext;

import kwetter.dao.icontext.IProfileContext;
import kwetter.model.models.Profile;
import kwetter.service.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@JPA
@Stateless
public class MySQLProfileContext implements IProfileContext {

    @PersistenceContext(unitName = "KwetterPU")
    private EntityManager entityManager;

    public MySQLProfileContext() {
    }
    public MySQLProfileContext(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void createProfile(Profile profile) {
        entityManager.persist(profile);
    }

    @Override
    public Profile update(Profile entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Profile create(Profile entity) {
        //TODO check username or smth
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Profile findbyId(long id) {
        Profile profile = entityManager.find(Profile.class, id);
        if(profile != null) return profile;

        return null;
    }

    @Override
    public void deleteById(long id) {
        Profile profile = entityManager.find(Profile.class, id);
        profile.getOwner().setProfile(null);
        for (Profile follower : profile.getFollowers()) {
            profile.removeFollower(follower);
            follower.removeFollowing(profile);
            entityManager.persist(follower);
        }
        for(Profile following : profile.getFollowing()){
            profile.removeFollowing(following);
            following.removeFollower(profile);
            entityManager.persist(following);
        }
        entityManager.persist(profile);
        entityManager.remove(profile);
    }

    @Override
    public List<Profile> getFollowing(long id) {
        Query query = entityManager.createQuery("SELECT p FROM Profile p join p.followers f where f.id = :id").setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Profile> getFollowers(long id) {
        Query query = entityManager.createQuery("SELECT p FROM Profile p join p.following f where f.id = :id").setParameter("id", id);
        return query.getResultList();
    }


}
