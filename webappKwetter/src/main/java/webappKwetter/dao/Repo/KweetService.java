package webappKwetter.dao.Repo;

import webappKwetter.dao.IContext.IKweetContext;
import webappKwetter.dao.IContext.IProfileContext;
import webappKwetter.dao.IContext.IUserContext;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class KweetService {
    @Inject @JPA
    private IKweetContext kweetContext;

    @Inject @JPA
    private IProfileContext profileContext;
    public KweetService() {
    }

    public Kweet create(Kweet kweet){
        Profile profile = profileContext.findbyId(kweet.getId());
        kweetContext.create(kweet);
        profile.addKweet(kweet);
        profileContext.update(profile);
        return kweet;
    }
}
