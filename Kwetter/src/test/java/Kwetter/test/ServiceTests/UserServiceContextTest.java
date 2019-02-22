

package Kwetter.test.ServiceTests;




import Kwetter.dao.IContext.IUserContext;
import Kwetter.dao.KwetterFacade;
import Kwetter.dao.MySqlContext.MySQLUserContext;
import Kwetter.dao.Service.UserService;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class UserServiceContextTest {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");

    private EntityManager entityManager;
    private EntityTransaction transaction;
    private UserService repo;

    public UserServiceContextTest() {

    }

    @Before
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
        transaction.commit();
    }


}