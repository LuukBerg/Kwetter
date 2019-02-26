package kwetter.test.ServiceTests;


import kwetter.dao.icontext.IProfileContext;
import kwetter.dao.mysqlcontext.MySQLProfileContext;
import kwetter.service.ProfileService;
import kwetter.model.enums.Role;
import kwetter.model.models.Details;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;

public class ProfileServiceTest {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private ProfileService profileService;
    private User user;
    @Before
    public void beforeEach() throws IOException {

        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        IProfileContext context = new MySQLProfileContext(entityManager);
        profileService = new ProfileService(context);

        transaction.begin();
        user = new User("testUser", Role.USER);
        entityManager.persist(user);
        transaction.commit();
    }


    @Test
    public void createProfile(){
        transaction.begin();
        Profile profile = new Profile(user, new Details("test", "test", "test","test"));
        profileService.create(profile);
        transaction.commit();

        Assert.assertEquals(1, profile.getId());
    }
    @Test
    public void updateProfile(){
        transaction.begin();
        Profile profile = new Profile(user, new Details("test", "test", "test","test"));
        profileService.create(profile);
        transaction.commit();
        Assert.assertEquals(1, profile.getId());
        Assert.assertEquals("test", profile.getDetails().getName());
        transaction.begin();
        profile.getDetails().setName("updatename");
        profileService.update(profile);
        transaction.commit();
        Profile getProfile = profileService.findbyId(profile.getId());
        Assert.assertEquals("updatename", getProfile.getDetails().getName());
    }

    @Test
    public void deleteProfile(){
        transaction.begin();
        Profile profile = new Profile(user, new Details("test", "test", "test","test"));
        profileService.create(profile);
        transaction.commit();
        Assert.assertEquals(1, profile.getId());
        Assert.assertEquals("test", profile.getDetails().getName());
        Profile found1 = profileService.findbyId(1);
        transaction.begin();
        profileService.deleteById(1);
        transaction.commit();
        transaction.begin();
        Profile found = profileService.findbyId(1);
        transaction.commit();
        Assert.assertNull(found);
    }

    @Test
    public void addFollow(){
        transaction.begin();
        Profile profile = new Profile(user, new Details("test", "test", "test","test"));
        Profile followProfile = new Profile(user, new Details("test", "test", "test","test"));
        profileService.create(profile);
        profileService.create(followProfile);
        transaction.commit();
        transaction.begin();
        profileService.addFollow(profile.getId(), followProfile.getId());
        transaction.commit();
        Profile found = profileService.findbyId(profile.getId());
        Profile followFound = profileService.findbyId(followProfile.getId());
        //Profile found1 = profileService.findbyId(4);
        Assert.assertEquals(1, found.getFollowers().size());
        Assert.assertEquals(1, followFound.getFollowing().size());
    }
    @Test
    public void unfollow(){
        transaction.begin();
        Profile profile = new Profile(user, new Details("test", "test", "test","test"));
        Profile followProfile = new Profile(user, new Details("test", "test", "test","test"));
        profileService.create(profile);
        profileService.create(followProfile);
        transaction.commit();
        transaction.begin();
        profileService.addFollow(profile.getId(), followProfile.getId());
        transaction.commit();
        Profile found = profileService.findbyId(profile.getId());
        Profile followFound = profileService.findbyId(followProfile.getId());
        Assert.assertEquals(1, found.getFollowers().size());
        Assert.assertEquals(1, followFound.getFollowing().size());

        transaction.begin();
        profileService.unFollow(profile.getId(),followProfile.getId());
        transaction.commit();
        Assert.assertEquals(0, found.getFollowers().size());
        Assert.assertEquals(0, followFound.getFollowing().size());
    }
}