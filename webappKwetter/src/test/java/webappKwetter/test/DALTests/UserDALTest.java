

package webappKwetter.test.DALTests;



import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webappKwetter.dao.KwetterFacade;
import webappKwetter.dao.MySqlContext.MySQLUserContext;
import webappKwetter.dao.MockContext.*;
import webappKwetter.dao.Repo.UserRepo;
import webappKwetter.model.Enums.Role;
import webappKwetter.model.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


class UserDALTest {

    KwetterFacade facade;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private MySQLUserContext userdoa;
    private UserRepo repo;
    @BeforeEach
    void beforeEach(){
        facade = new KwetterFacade(new MockUserContext(), new MockProfileContext());
    }
    @BeforeEach
    public void setUp(){
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        repo = new UserRepo(new MySQLUserContext(entityManager));
    }
    @Test
    void registerUser(){
        transaction.begin();
        User user = repo.registerUser("testuser");
        transaction.commit();
        Assert.assertEquals(1,user.getId());
        transaction.begin();
        User user2 = repo.registerUser("testuser2");
        transaction.commit();
        Assert.assertEquals(2,user2.getId());
    }
    @Test
    void loginUser() {
        facade.loginUser("testUser");
        Assert.assertEquals(facade.getUser(), "testUser");
        Assert.assertEquals(facade.getUser().getRole(), Role.user);
        Assert.assertNotNull(facade.getUser().getProfile());
    }
    @Test
    void logoutUser(){
        facade.loginUser("testUser");
        Assert.assertEquals(facade.getUser(), "testUser");
        facade.logoutUser();
        Assert.assertNull(facade.getUser());
    }

}