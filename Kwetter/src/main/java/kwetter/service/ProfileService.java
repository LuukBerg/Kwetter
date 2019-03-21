package kwetter.service;


import kwetter.dao.icontext.IProfileContext;
import kwetter.model.models.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ProfileService implements Serializable {
    @Inject @JPA
    private IProfileContext context;
    public ProfileService(IProfileContext context) {
        this.context = context;
    }


    public ProfileService() {
    }


    public Profile update(Profile entity) {
        return context.update(entity);

    }

    public Profile create(Profile entity) {
        return context.create(entity);
    }

    public Profile findbyId(long id) {
        return context.findbyId(id);
    }


    public void deleteById(long id) {
        context.deleteById(id);
    }

    public void addFollow(long id, long followingId) {
        if(followingId != id){
            Profile followerProfile = context.findbyId(id);
            Profile profile = context.findbyId(followingId);
            if(profile != null && followerProfile != null){
                profile.addFollower(followerProfile);
                followerProfile.addFollowing(profile);
                context.update(profile);
                context.update(followerProfile);
            }

        }

    }
    public void unFollow(long id, long followingId){
        if(followingId != id){
            Profile followingProfile = context.findbyId(id);
            Profile profile = context.findbyId(followingId);
            if(profile != null && followingProfile != null){
                followingProfile.removeFollowing(profile);
                profile.removeFollower(followingProfile);
                context.update(profile);
                context.update(followingProfile);
            }

        }
    }

    public List<Profile> getFollowing(long id) {
        return context.getFollowing(id);
    }

    public List<Profile> getFollowers(long id) {
        return context.getFollowers(id);
    }
}
