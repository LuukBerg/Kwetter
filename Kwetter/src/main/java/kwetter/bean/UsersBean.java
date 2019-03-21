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
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("deletebyId: " + id);
        System.out.println(userService);
        userService.deleteById(id);
    }
    public void setRole(Role role, long id){
        User user = userService.findById(id);
        userService.updateRole(role, user);
    }
}
