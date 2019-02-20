

package Kwetter.test.DAOTests;


import Kwetter.dao.MySqlContext.MySQLKweetContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Kwetter.dao.IContext.IKweetContext;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.Details;
import Kwetter.model.Models.Kweet;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class KweetDALTest {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");

    private EntityManager entityManager;
    private EntityTransaction transaction;

    private IKweetContext context;
    private Profile profile;
    public KweetDALTest() {


    }

    @Before
    public void Before(){
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        context = new MySQLKweetContext(entityManager);
        transaction.begin();
        User user = new User("testUser", Role.user);
        entityManager.persist(user);
        profile = new Profile(user, new Details("test","test","test", "test"));
        entityManager.persist(profile);
        transaction.commit();
    }
    @Test
    public void createKweet(){
        transaction.begin();
        Kweet kweet = new Kweet("testkweet", profile);
        context.create(kweet);
        transaction.commit();
        Assert.assertEquals(1,kweet.getId());

    }
    @Test
    public void findKweetByProfile() {
        transaction.begin();
        for (int i =0; i <10 ; i++){

            Kweet kweet = new Kweet("testkweet" + i, profile);
            context.create(kweet);

        }
        transaction.commit();
        transaction.begin();
        List<Kweet> kweets = context.findByProfile(profile.getId());
        Assert.assertEquals(10, kweets.size());
        transaction.commit();
    }
    @Test
    public void findKweetById(){
        transaction.begin();
        Kweet kweet = new Kweet("testkweet", profile);
        context.create(kweet);
        transaction.commit();
        Assert.assertEquals(1,kweet.getId());
        Kweet findkweet = context.findKweetById(1);
        Assert.assertEquals(kweet, findkweet);
    }

}