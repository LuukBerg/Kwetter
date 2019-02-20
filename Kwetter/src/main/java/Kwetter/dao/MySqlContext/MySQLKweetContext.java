package Kwetter.dao.MySqlContext;

import Kwetter.model.Models.Profile;
import Kwetter.dao.IContext.IKweetContext;
import Kwetter.dao.Repo.JPA;
import Kwetter.model.Models.Kweet;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@JPA
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
        Query query = entityManager.createQuery("SELECT k FROM Kweet k where owner.id = :id order by date").setParameter("id", profile.getId()).setMaxResults(10);
        return query.getResultList();
    }

    @Override
    public List<Kweet> getAllOrderedByDate() {
        Query query = entityManager.createQuery("SELECT k FROM Kweet k ORDER BY date");
        return query.getResultList();
    }

    @Override
    public void delete(Kweet kweet) {
        entityManager.remove(kweet);
    }
}
