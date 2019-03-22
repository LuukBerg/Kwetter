

package kwetter.test.ServiceTests;




import kwetter.dao.icontext.IUserContext;
import kwetter.dao.mysqlcontext.MySQLUserContext;
import kwetter.model.models.Details;
import kwetter.model.models.Profile;
import kwetter.service.UserService;
import kwetter.model.enums.Role;
import kwetter.model.models.User;
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
        User user = repo.registerUser(new User("testuser", Role.USER));
        transaction.commit();
        Assert.assertEquals(1,user.getId());
        transaction.begin();
        User user2 = repo.registerUser(new User("testuser2", Role.USER));
        transaction.commit();
        Assert.assertEquals(2,user2.getId());
    }
    @Test
    public void loginUser() {
        transaction.begin();
        User user = repo.registerUser(new User("testuser", Role.USER));
        transaction.commit();
        transaction.begin();
        User loginUser = repo.loginUser("testuser");
        Assert.assertEquals(user, loginUser);
        transaction.commit();
    }

    @Test
    public void removeUser(){
        transaction.begin();
        User user = new User("testuser", "email", "password");
        Profile profile = new Profile(user, new Details("test","test","test","test"));
        repo.registerUser(user);
        transaction.commit();
        transaction.begin();
        User found = repo.findByUsername("testuser");
        transaction.commit();
        Assert.assertNotNull(found);
        transaction.begin();
        repo.deleteById(user.getId());
        transaction.commit();
        transaction.begin();
        user = repo.findByUsername("testuser");
        transaction.commit();
        Assert.assertNull(user);
    }
    @Test
    public void updateRole(){
        transaction.begin();
        User user = new User("testuser", "email", "password");
        Profile profile = new Profile(user, new Details("test","test","test","test"));
        repo.registerUser(user);
        transaction.commit();
        transaction.begin();
        repo.updateRole(Role.MOD,user.getId());
        transaction.commit();
        transaction.begin();
        User found = repo.findByUsername("testuser");
        transaction.commit();
        Assert.assertNotNull(found);
        Assert.assertEquals(Role.MOD, found.getRole());
    }

}