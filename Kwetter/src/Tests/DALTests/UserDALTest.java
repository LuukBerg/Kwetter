

package Tests.DALTests;

import DAL.IContext.IUserContext;
import DAL.MockContext.MockProfileContext;
import DAL.MockContext.MockUserContext;
import DAL.Repo.UserRepo;
import Enums.Role;
import Facade.KwetterFacade;
import Models.Details;
import Models.Profile;
import Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;


class UserDALTest {

    KwetterFacade facade;

    @BeforeEach
    void beforeEach(){
        facade = new KwetterFacade(new MockUserContext(), new MockProfileContext());
    }
    @Test
    void registerUser(){
        facade.loginUser("registerTestUser");
        Assert.assertNull(facade.getUser());
        facade.registerUser("registerTestUser");
        facade.loginUser("registerTestUser");
        Assert.assertNotNull(facade.getUser());
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