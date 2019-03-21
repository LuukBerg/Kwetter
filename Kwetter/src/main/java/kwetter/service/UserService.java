package kwetter.service;

import kwetter.dao.icontext.IUserContext;
import kwetter.model.enums.Role;
import kwetter.model.models.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UserService implements Serializable {
    @Inject @JPA
    private IUserContext context;


    public UserService() {
    }
    public UserService(IUserContext context){
        this.context = context;
    }

    public User registerUser(User user){
        if(context.findByUsername(user.getUsername()) == null){
            return context.create(user);
        }
        return null;
    }

    public User loginUser(String username){
        return context.findByUsername(username);
    }
    public User findByUsername(String username){return context.findByUsername(username);}

    public User findById(long id) {
        return context.findbyId(id);
    }

    public void updateUsername(String username, User user){
        if(context.findByUsername(username) == null){
            user.setUsername(username);
            context.update(user);
        }
    }
    public void updateRole(Role role, User user) {
        user.setRole(role);
        context.update(user);
    }
    public void deleteById(long id) {
        context.deleteById(id);
    }

    public List<User> getAllUsers(){
       return context.getAll();
    }
}
