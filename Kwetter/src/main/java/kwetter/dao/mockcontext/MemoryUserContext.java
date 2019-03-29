package kwetter.dao.mockcontext;


import kwetter.model.models.User;
import kwetter.dao.icontext.IUserContext;
import kwetter.service.Mock;

import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;
@Default
@Stateful
@Mock
public class MemoryUserContext implements IUserContext {
    List<User> users = new ArrayList<>();
    private long idIncrement = 0;


    @Override
    public void update(User entity) {
        for(User user : users){
            if(user.getId() == entity.getId()){
                 users.remove(user);
                 users.add(entity);
            }
        }
    }

    @Override
    public User create(User entity) {
        entity.setId(idIncrement++);
        users.add(entity);
        return entity;
    }

    @Override
    public User findbyId(long id) {
        for(User user : users){
            if(user.getId() == id)return user;
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
        User userToRemove = null;
        for(User user : users){
            if(user.getId() == id) userToRemove = user;
        }
        users.remove(userToRemove);

    }

    @Override
    public User findByUsername(String username) {
        for(User user : users){
            if(user.getUsername().equals(username)) return user;
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User login(String username, byte[] hashed) {
        return null;
    }
}
