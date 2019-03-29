package kwetter.bean;

import kwetter.model.enums.Role;
import kwetter.model.models.User;
import kwetter.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class UserServiceBean {

    @Inject
    private UserService userService;
    private static final Map<String, User> USER_DB = new HashMap<String, User>() {{
        User user = new User("admin", "email", "password");
        user.setRole(Role.MOD);
        put("username", new User("username", "email", "password"));
        put("admin", user);
    }};

    public User authenticate(final String username, final String password) throws Exception {
        User user = userService.loginUser(username, password);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new Exception("Failed logging in org.jboss.user with name '" + username + "': unknown username or wrong password");
    }
}
