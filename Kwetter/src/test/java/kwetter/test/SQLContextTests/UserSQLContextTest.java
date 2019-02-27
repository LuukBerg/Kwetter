package kwetter.test.SQLContextTests;

import kwetter.dao.icontext.IProfileContext;
import kwetter.dao.icontext.IUserContext;
import kwetter.dao.mysqlcontext.MySQLProfileContext;
import kwetter.dao.mysqlcontext.MySQLUserContext;
import kwetter.model.enums.Role;
import kwetter.model.models.Details;
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

import static org.junit.Assert.*;

public class UserSQLContextTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");

    private EntityManager entityManager;
    private EntityTransaction transaction;

    private IProfileContext profileContext;
    private IUserContext userContext;
    private Profile profile;

    @Before
    public void Before(){
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        profileContext = new MySQLProfileContext(entityManager);
        userContext = new MySQLUserContext(entityManager);
        transaction.begin();
        User user = new User("testUser", Role.USER);
        profile = new Profile(user, new Details("test","test","test", "test"));
        entityManager.persist(user);
        transaction.commit();
    }
    @After
    public void after(){
        entityManager.close();
    }
    //changes to profile should be updated with user
    @Test
    public void update() {
        User user = new User("username", "email", "pass");
        Profile profile = new Profile(user, new Details("test","test","test", "test" ));
        transaction.begin();
        userContext.create(user);
        transaction.commit();
        transaction.begin();
        user.getProfile().getDetails().setName("changed");
        user.setEmail("changed");
        userContext.update(user);
        transaction.commit();
        transaction.begin();
        User found = userContext.findByUsername("username");
        transaction.commit();
        Assert.assertEquals("changed", found.getProfile().getDetails().getName());
        Assert.assertEquals("changed", found.getEmail());

    }

    //user can be created without profile
    @Test
    public void create() {
        User user = new User("username", "email", "pass");
        transaction.begin();
        userContext.create(user);
        transaction.commit();
        transaction.begin();
        User found = userContext.findbyId(2);
        transaction.commit();
        Assert.assertNotNull(found);
        Assert.assertNull(found.getProfile());
    }

    //when removing user profile should also be removed
    @Test
    public void deleteById() {
        User user = new User("username", "email", "pass");
        Profile profile = new Profile(user, new Details("test","test","test", "test" ));
        transaction.begin();
        userContext.create(user);
        transaction.commit();
        transaction.begin();
        User found = userContext.findbyId(2);
        Profile foundProfile = profileContext.findbyId(2);
        transaction.commit();
        Assert.assertNotNull(found);
        Assert.assertNotNull(foundProfile);
        Assert.assertEquals(found.getProfile(), foundProfile);
        transaction.begin();
        userContext.deleteById(2);
        transaction.commit();
        transaction.begin();
        found = userContext.findbyId(2);
        foundProfile = profileContext.findbyId(2);
        transaction.commit();
        Assert.assertNull(found);
        Assert.assertNull(foundProfile);


    }

    @Test
    public void findByUsername() {

        User user = new User("username", "email", "pass");
        transaction.begin();
        userContext.create(user);
        transaction.commit();
        transaction.begin();
        User found = userContext.findByUsername("username");
        transaction.commit();
        Assert.assertEquals(user ,found);
    }
}