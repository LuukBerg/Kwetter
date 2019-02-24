package Kwetter.dao.Service;

import Kwetter.dao.IContext.IUserContext;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {
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

}
