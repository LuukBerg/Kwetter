package kwetter.test.SQLContextTests;

import kwetter.dao.icontext.IKweetContext;
import kwetter.dao.icontext.IProfileContext;
import kwetter.dao.icontext.IUserContext;
import kwetter.dao.mysqlcontext.MySQLKweetContext;
import kwetter.dao.mysqlcontext.MySQLProfileContext;
import kwetter.dao.mysqlcontext.MySQLUserContext;
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

import static org.junit.Assert.*;

public class ProfileSQLContextTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");

    private EntityManager entityManager;
    private EntityTransaction transaction;

    private IProfileContext context;
    private IUserContext userContext;
    private IKweetContext kweetContext;
    private Profile profile;

    @Before
    public void Before(){
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        context = new MySQLProfileContext(entityManager);
        userContext = new MySQLUserContext(entityManager);
        kweetContext = new MySQLKweetContext(entityManager);
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

    //create profile also creates user
    @Test
    public void createProfile() {
        User user = new User("testname", "testemail", "pass");
        Profile profile = new Profile(user,new Details("test","test","test", "test"));
        transaction.begin();
        context.create(profile);
        transaction.commit();
        transaction.begin();
        Profile profileFound = context.findbyId(2);
        transaction.commit();
        Assert.assertNotNull(profileFound);
    }

    @Test
    public void update() {
        transaction.begin();
        Profile found1 = context.findbyId(1);
        Assert.assertEquals(found1.getDetails().getName(), "test");
        transaction.commit();
        found1.getDetails().setName("changed");
        transaction.begin();
        context.update(found1);
        transaction.commit();
        transaction.begin();
        Profile found2 = context.findbyId(1);
        transaction.commit();
        Assert.assertEquals("changed", found2.getDetails().getName());

    }


    //when profile is deleted, user should keep existing
    @Test
    public void deleteById() {
        User user = new User("username", "email", "pass");
        Profile profile = new Profile(user, new Details("test","test","test", "test" ));
        User followUser = new User("follow", "email", "pass");
        Profile followProfile = new Profile(followUser, new Details("test","test","test", "test" ));
        transaction.begin();
        userContext.create(user);
        userContext.create(followUser);
        transaction.commit();
        transaction.begin();
        Kweet kweet = new Kweet("testcontent", profile);
        kweetContext.create(kweet);
        transaction.commit();
        transaction.begin();
        profile.addFollower(followProfile);
        followProfile.addFollowing(profile);
        context.update(profile);
        context.update(followProfile);
        transaction.commit();
        transaction.begin();
        User found = userContext.findbyId(2);
        Profile foundProfile = context.findbyId(2);
        User followFound = userContext.findbyId(3);
        Profile followFoundProfile = context.findbyId(3);
        transaction.commit();
        Assert.assertNotNull(found);
        Assert.assertNotNull(foundProfile);
        Assert.assertEquals(foundProfile.getKweets().get(0), kweet);
        Assert.assertEquals(found.getProfile(), foundProfile);
        Assert.assertNotNull(followFound);
        Assert.assertNotNull(followFoundProfile);
        Assert.assertEquals(foundProfile.getFollowers().get(0), followFoundProfile);
        Assert.assertEquals(followFoundProfile.getFollowing().get(0), foundProfile);
        transaction.begin();
        context.deleteById(2);
        transaction.commit();
        transaction.begin();
        found = userContext.findbyId(2);
        foundProfile = context.findbyId(2);
        followFound = userContext.findbyId(3);
        followFoundProfile = context.findbyId(3);
        transaction.commit();
        Assert.assertNotNull(found);
        Assert.assertNull(foundProfile);
        Assert.assertNull(kweetContext.findKweetById(kweet.getId()));
        Assert.assertNotNull(followFound);
        Assert.assertNotNull(followFoundProfile);
        Assert.assertEquals(followFoundProfile.getFollowing().size(), 0);


    }
}