package webappKwetter.dao.MockContext;

import webappKwetter.dao.IContext.IProfileContext;
import webappKwetter.model.Models.Details;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;
import webappKwetter.model.Models.User;

import java.util.List;

public class MockProfileContext implements IProfileContext {


    @Override
    public Profile update(Profile entity) {
        return null;
    }

    @Override
    public Profile create(Profile entity) {
        return null;
    }

    @Override
    public Profile findbyId(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
