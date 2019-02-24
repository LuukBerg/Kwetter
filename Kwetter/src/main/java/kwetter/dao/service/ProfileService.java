package kwetter.dao.service;


import kwetter.dao.icontext.IProfileContext;
import kwetter.model.models.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ProfileService {
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
            Profile followingProfile = context.findbyId(followingId);
            Profile profile = context.findbyId(id);
            if(profile != null && followingProfile != null){
                profile.addFollower(followingProfile);
                followingProfile.addFollowing(profile);
                context.update(profile);
                context.update(followingProfile);
            }

        }

    }
    public void unFollow(long id, long followingId){
        if(followingId != id){
            Profile followingProfile = context.findbyId(followingId);
            Profile profile = context.findbyId(id);
            if(profile != null && followingProfile != null){
                followingProfile.removeFollowing(profile);
                profile.removeFollower(followingProfile);
                context.update(profile);
                context.update(followingProfile);
            }

        }
    }
}
