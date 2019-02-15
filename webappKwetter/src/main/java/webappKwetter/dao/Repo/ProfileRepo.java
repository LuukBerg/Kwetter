package webappKwetter.dao.Repo;


import webappKwetter.dao.IContext.IProfileContext;
import webappKwetter.model.Models.Details;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;
import webappKwetter.model.Models.User;

import javax.inject.Inject;
import java.util.List;

public class ProfileRepo {
    @Inject @JPA
    private IProfileContext context;

    public ProfileRepo(IProfileContext context) {
        this.context = context;
    }

    public ProfileRepo() {
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
}
