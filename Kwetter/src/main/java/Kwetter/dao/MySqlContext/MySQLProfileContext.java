package Kwetter.dao.MySqlContext;

import Kwetter.dao.IContext.IProfileContext;
import Kwetter.model.Models.Profile;
import Kwetter.dao.Service.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        entityManager.remove(profile);
    }
}
