package kwetter.bean;

import kwetter.model.enums.Role;
import kwetter.model.models.User;
import kwetter.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "usersBean")
@SessionScoped
public class UsersBean implements Serializable {

    @Inject
    private UserService userService;

    public List<User> getUsers(){
        return userService.getAllUsers();
    }
    public void deleteUser(long id){
        userService.deleteById(id);
    }
    public void setRole(String role, long id){
        Role r = Role.valueOf(role);
        User user = userService.findById(id);
        userService.updateRole(r, id);
    }
}
