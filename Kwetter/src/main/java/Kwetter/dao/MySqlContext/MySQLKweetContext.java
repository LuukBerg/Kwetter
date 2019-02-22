package Kwetter.dao.MySqlContext;

import Kwetter.model.Models.Profile;
import Kwetter.dao.IContext.IKweetContext;
import Kwetter.dao.Service.JPA;
import Kwetter.model.Models.Kweet;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@JPA
@Stateless
public class MySQLKweetContext implements IKweetContext {

    @PersistenceContext(unitName = "KwetterPU")
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
        Query query = entityManager.createQuery("SELECT k FROM Kweet k where owner.id = :id order by date desc").setParameter("id", profile.getId()).setMaxResults(10);
        return query.getResultList();
    }

    @Override
    public List<Kweet> getAllOrderedByDate() {
        Query query = entityManager.createQuery("SELECT k FROM Kweet k ORDER BY date desc");
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Kweet.class,id));
    }


    @Override
    public Kweet update(Kweet kweet){
        entityManager.persist(kweet);
        return kweet;
    }
}
