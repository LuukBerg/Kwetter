package webappKwetter.dao.Repo;


import org.hibernate.service.spi.InjectService;
import webappKwetter.dao.IContext.IUserContext;
import webappKwetter.model.Models.User;

import javax.inject.Inject;

public class UserRepo {
    @Inject @JPA
    private IUserContext context;

    public UserRepo() {
    }
    public UserRepo(IUserContext context){
        this.context = context;
    }

    public User registerUser(User user){
        return context.create(user);
    }

    public User loginUser(String username){
        return context.findByUsername(username);
    }

}
