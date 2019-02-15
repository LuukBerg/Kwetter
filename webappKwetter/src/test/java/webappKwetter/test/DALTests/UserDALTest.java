

package webappKwetter.test.DALTests;



import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webappKwetter.dao.IContext.IUserContext;
import webappKwetter.dao.KwetterFacade;
import webappKwetter.dao.MySqlContext.MySQLUserContext;
import webappKwetter.dao.Repo.UserRepo;
import webappKwetter.model.Enums.Role;
import webappKwetter.model.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class UserDALTest {

    KwetterFacade facade;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");

    private EntityManager entityManager;
    private EntityTransaction transaction;
    private UserRepo repo;

    public UserDALTest() {

    }

    @BeforeEach
    public void setUp(){
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        IUserContext context = new MySQLUserContext(entityManager);
        repo = new UserRepo(context);
    }
    @Test
    public void registerUser(){
        transaction.begin();
        User user = repo.registerUser(new User("testuser", Role.user));
        transaction.commit();
        Assert.assertEquals(1,user.getId());
        transaction.begin();
        User user2 = repo.registerUser(new User("testuser2", Role.user));
        transaction.commit();
        Assert.assertEquals(2,user2.getId());
    }
    @Test
    public void loginUser() {
        facade.loginUser("testUser");
        Assert.assertEquals(facade.getUser(), "testUser");
        Assert.assertEquals(facade.getUser().getRole(), Role.user);
        Assert.assertNotNull(facade.getUser().getProfile());
    }
    @Test
    public void logoutUser(){
        facade.loginUser("testUser");
        Assert.assertEquals(facade.getUser(), "testUser");
        facade.logoutUser();
        Assert.assertNull(facade.getUser());
    }

}