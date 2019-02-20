package Kwetter.dao.Repo;

import Kwetter.dao.IContext.IUserContext;
import Kwetter.model.Enums.Role;
import Kwetter.model.Models.User;

import javax.inject.Inject;

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

    public void updateRole(Role role, User user) {
        user.setRole(role);
        context.update(user);
    }

}
