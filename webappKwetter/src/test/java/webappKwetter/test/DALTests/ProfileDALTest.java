package webappKwetter.test.DALTests;


import org.junit.Assert;
import org.junit.Before;
import webappKwetter.dao.IContext.IProfileContext;
import webappKwetter.dao.IContext.IUserContext;
import webappKwetter.dao.KwetterFacade;
import webappKwetter.dao.MockContext.MockProfileContext;
import webappKwetter.dao.MySqlContext.MySQLProfileContext;
import webappKwetter.dao.MySqlContext.MySQLUserContext;
import webappKwetter.dao.Repo.ProfileRepo;
import webappKwetter.dao.Repo.UserRepo;
import webappKwetter.model.Enums.Role;
import webappKwetter.model.Models.Details;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import webappKwetter.model.Models.User;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProfileDALTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private ProfileRepo repo;
    private KwetterFacade facade;
    private User user;
    @Before
    public  void beforeEach() throws IOException {
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        IProfileContext context = new MySQLProfileContext(entityManager);
        repo = new ProfileRepo(context);

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