package kwetter.test.ServiceTests;

import kwetter.dao.icontext.IKweetContext;
import kwetter.dao.mysqlcontext.MySQLKweetContext;
import kwetter.dao.mysqlcontext.MySQLProfileContext;
import kwetter.dao.service.KweetService;
import kwetter.model.enums.Role;
import kwetter.model.models.Details;
import kwetter.model.models.Kweet;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class KweetServiceTest {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private KweetService kweetService;
    private Profile profile;
    private User user;
    @Before
    public void beforeEach() throws IOException {

        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        IKweetContext context = new MySQLKweetContext(entityManager);
        kweetService = new KweetService(context, new MySQLProfileContext(entityManager));
        transaction.begin();
        user = new User("testuser", Role.USER);
        profile = new Profile(user, new Details("test","test","test", "test"));
        entityManager.persist(user);
        entityManager.persist(profile);
        transaction.commit();
    }
    @After
    public void after(){

    }
    @Test
    public void create() {
        Kweet kweet = new Kweet("test", profile);
        kweetService.create(kweet);
        Assert.assertNotNull(kweet.getId());
    }

    @Test
    public void findByProfile() {
        transaction.begin();
        for(int i=0; i < 10; i++){
            kweetService.create(new Kweet("test" + i, profile));
        }
        transaction.commit();
        transaction.begin();
        List<Kweet> result = kweetService.findByProfile(profile.getId());
        transaction.commit();
        Assert.assertEquals(10,result.size());
    }

    @Test
    public void getAllOrderedByDate() {
        transaction.begin();
        Random r = new Random();
        for(int i=0; i < 10; i++){
            Kweet kweet = new Kweet("test" + i, profile);
            kweet.setDate(new Date(r.nextInt()));
            kweetService.create(kweet);
        }
        transaction.commit();
        transaction.begin();
        List<Kweet> kweets = kweetService.getAllOrderedByDate();
        transaction.commit();
        Assert.assertEquals(1,kweets.get(0).getDate().compareTo(kweets.get(1).getDate()));

    }

    @Test
    public void delete() {
        transaction.begin();
        Kweet kweet = new Kweet("test", profile);
        kweetService.create(kweet);
        Assert.assertNotNull(kweet.getId());
        transaction.commit();
        transaction.begin();
        kweetService.delete(kweet.getId());
        transaction.commit();
        transaction.begin();
        Kweet found = kweetService.findById(1);
        transaction.commit();
        Assert.assertNull(found);
        transaction.begin();
        Profile foundProfile = entityManager.find(Profile.class, profile.getId());
        transaction.commit();
        Assert.assertNotNull(foundProfile);

    }
    @Test
    public void addHearth() {
    }
}