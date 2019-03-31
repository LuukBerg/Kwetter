package kwetter.service;

import kwetter.dao.icontext.IUserContext;
import kwetter.model.enums.Role;
import kwetter.model.models.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        String unhashed = user.getPassword();
        byte[] hashed = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hashed = digest.digest(
                    unhashed.getBytes(StandardCharsets.UTF_8));
            user.setHashedPassword(hashed);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        if(context.findByUsername(user.getUsername()) == null){
            return context.create(user);
        }
        return null;
    }

    public User loginUser(String username, String password){
        byte[] hashed = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hashed = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return context.login(username, hashed);
    }
    public User findByUsername(String username){return context.findByUsername(username);}

    public List<User> findPartialUsername(String partialName){
        return context.findPartialUsername(partialName);
    }
    public User findById(long id) {
        return context.findbyId(id);
    }

    public void updateUsername(String username, User user){
        if(context.findByUsername(username) == null){
            user.setUsername(username);
            context.update(user);
        }
    }
    public void updateRole(Role role, long id) {
        User user = context.findbyId(id);
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
