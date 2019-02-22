package Kwetter.test.ServiceTests;

import Kwetter.dao.IContext.IKweetContext;
import Kwetter.dao.IContext.IProfileContext;
import Kwetter.dao.MySqlContext.MySQLKweetContext;
import Kwetter.dao.MySqlContext.MySQLProfileContext;
import Kwetter.dao.Service.KweetService;
import Kwetter.dao.Service.ProfileService;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.Details;
import Kwetter.model.Models.Kweet;
import Kwetter.model.Models.Profile;
import Kwetter.model.Models.User;
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

import static org.junit.Assert.*;

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
        user = new User("testuser", Role.user);
        profile = new Profile(user, new Details("test","test","test", "test"));
        entityManager.persist(user);
        entityManager.persist(profile);
        transaction.commit();
    }
    @Test
    public void create() {
        Kweet kweet = new Kweet("test", profile);
        kweetService.create(kweet);
        Assert.assertNotNull(kweet.getId());
    }
    //TODO fix
    @Test
    public void findByProfile() {
        transaction.begin();
        for(int i=0; i < 10; i++){
            kweetService.create(new Kweet("test" + i, profile));
        }
        transaction.commit();
        List<Kweet> result = kweetService.findByProfile(profile.getId());
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
        List<Kweet> kweets = kweetService.getAllOrderedByDate();
        Assert.assertEquals(1,kweets.get(0).getDate().compareTo(kweets.get(1).getDate()));

    }

    //TODO fix
    @Test
    public void delete() {
        transaction.begin();
        Kweet kweet = new Kweet("test", profile);
        kweetService.create(kweet);
        Assert.assertNotNull(kweet.getId());
        transaction.commit();
        transaction.begin();
        kweetService.delete(kweet.getId(), profile);
        transaction.commit();
        Kweet found = kweetService.findById(1);
        Assert.assertNull(found);

    }
    @Test
    public void addHearth() {
    }
}