

package Kwetter.test.DAOTests;



import org.junit.Assert;

import Kwetter.dao.IContext.IUserContext;
import Kwetter.dao.KwetterFacade;
import Kwetter.dao.MySqlContext.MySQLUserContext;
import Kwetter.dao.Repo.UserService;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class UserDALTest {

    KwetterFacade facade;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");

    private EntityManager entityManager;
    private EntityTransaction transaction;
    private UserService repo;

    public UserDALTest() {

    }

    @BeforeEach
    public void setUp(){
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        IUserContext context = new MySQLUserContext(entityManager);
        repo = new UserService(context);
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
        transaction.begin();
        User user = repo.registerUser(new User("testuser", Role.user));
        transaction.commit();
        transaction.begin();
        User loginUser = repo.loginUser("testuser");
        Assert.assertEquals(user, loginUser);
    }


}