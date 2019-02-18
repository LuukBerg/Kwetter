package webappKwetter.dao.MySqlContext;

import webappKwetter.dao.IContext.IProfileContext;
import webappKwetter.dao.Repo.JPA;
import webappKwetter.model.Models.Details;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;
import webappKwetter.model.Models.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

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
