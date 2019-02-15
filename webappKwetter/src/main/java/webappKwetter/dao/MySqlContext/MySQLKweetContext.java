package webappKwetter.dao.MySqlContext;

import webappKwetter.dao.IContext.IKweetContext;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MySQLKweetContext implements IKweetContext {
    @Inject @MySQLDatabase
    private EntityManager entityManager;

    public MySQLKweetContext(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public MySQLKweetContext() {
    }

    @Override
    public Kweet create(Kweet kweet) {
        if(!kweet.getContent().isEmpty()){
            if(kweet.getContent().length() < 141){
                entityManager.persist(kweet);
                return kweet;
            }
        }
        return null;
    }

    @Override
    public Kweet findKweetById(long id) {
        return entityManager.find(Kweet.class, id);
    }

    @Override
    public List<Kweet> findByProfile(long id) {
        Query query = entityManager.createQuery("SELECT k FROM Kweet k WHERE k.owner.id = :id").setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Kweet> getTimeLine(Profile profile) {
        return null;
    }
}
