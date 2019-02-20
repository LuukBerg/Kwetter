package Kwetter.dao.MySqlContext;

import Kwetter.dao.IContext.IProfileContext;
import Kwetter.model.Models.Profile;
import Kwetter.dao.Repo.JPA;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@JPA
public class MySQLProfileContext implements IProfileContext {
    @Inject
    @MySQLDatabase
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
        return entityManager.find(Profile.class, id);
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(entityManager.find(Profile.class, id));
    }
}
