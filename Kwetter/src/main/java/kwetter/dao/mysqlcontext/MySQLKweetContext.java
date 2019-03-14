package kwetter.dao.mysqlcontext;

import kwetter.model.models.Profile;
import kwetter.dao.icontext.IKweetContext;
import kwetter.service.JPA;
import kwetter.model.models.Kweet;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Console;
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
        if(!kweet.getContent().isEmpty() && kweet.getContent().length() < 141){
                entityManager.persist(kweet);
                return kweet;

        }
        return null;
    }

    @Override
    public Kweet findKweetById(long id) {
        return entityManager.find(Kweet.class, id);
    }

    @Override
    public List<Kweet> findByProfile(long id) {
        Query query = entityManager.createQuery("SELECT distinct k FROM Kweet k WHERE k.owner.id = :id").setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Kweet> getTimeLine(Profile profile, int offset) {
        //TODO find query that works. selects all kweets from all profiles caller follows
        //Select * FROM kweet k inner join profile on k.owner_id = 1 and profile.profile_id = 1
        //Select * FROM kweet k inner join profile on k.owner_id = profile.id where profile.id in (1,2)

        //select k from Kweet k inner join Profile on k.id = Profile.id where Profile.id in (:id) order by k.date desc
        //Query query = entityManager.createQuery("SELECT p.id FROM Profile p join p.followers f where f.id = :id").setParameter("id", profile.getId());
        //System.out.println(query.getResultList());
        Query query = entityManager.createQuery("SELECT k from Kweet k inner join k.owner p where p.id in (SELECT p.id FROM Profile p join p.followers f where f.id = :id) order by k.date desc").setParameter("id", profile.getId()).setFirstResult(offset).setMaxResults(10);
        //SELECT * FROM Kweet  where owner_id in (SELECT p.following_id FROM kwetter_test.profile_profile p where followers_id = 1)
        //Query query = entityManager.createNativeQuery("SELECT * FROM Kweet where owner_id in (SELECT p.following_id FROM profile_profile p where followers_id = :id) order by date desc").setParameter("id", profile.getId()).setFirstResult(offset).setMaxResults(10);
        return query.getResultList();
    }

    @Override
    public List<Kweet> getAllOrderedByDate() {
        Query query = entityManager.createQuery("SELECT k FROM Kweet k ORDER BY k.date desc");
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Kweet kweet = entityManager.find(Kweet.class, id );
        entityManager.remove(kweet);
    }


    @Override
    public Kweet update(Kweet kweet){
        entityManager.persist(kweet);
        return kweet;
    }
}
