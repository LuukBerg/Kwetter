package Kwetter.test.test.DAOTests;


import org.junit.Assert;
import org.junit.Before;
import Kwetter.dao.IContext.IProfileContext;
import Kwetter.dao.KwetterFacade;
import Kwetter.dao.MySqlContext.MySQLProfileContext;
import Kwetter.dao.Repo.ProfileService;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.Details;
import Kwetter.model.Models.Profile;
import org.junit.Test;
import Kwetter.model.Models.User;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProfileDALTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private ProfileService repo;
    private KwetterFacade facade;
    private User user;
    @Before
    public  void beforeEach() throws IOException {
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        IProfileContext context = new MySQLProfileContext(entityManager);
        repo = new ProfileService(context);

        transaction.begin();
        user = new User("testUser", Role.user);
        entityManager.persist(user);
        transaction.commit();
    }


    @Test
    public void createProfile(){
        transaction.begin();
        Profile profile = new Profile(user, new Details("test", "test", "test","test"));
        repo.create(profile);
        transaction.commit();

        Assert.assertEquals(1, profile.getId());
    }
    @Test
    public void updateProfile(){
        transaction.begin();
        Profile profile = new Profile(user, new Details("test", "test", "test","test"));
        repo.create(profile);
        transaction.commit();
        Assert.assertEquals(1, profile.getId());
        Assert.assertEquals("test", profile.getDetails().getName());
        transaction.begin();
        profile.getDetails().setName("updatename");
        repo.update(profile);
        transaction.commit();
        Profile getProfile = repo.findbyId(profile.getId());
        Assert.assertEquals("updatename", getProfile.getDetails().getName());
    }
}