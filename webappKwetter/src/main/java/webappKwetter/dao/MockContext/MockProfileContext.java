package DAL.MockContext;

import DAL.IContext.IProfileContext;
import webappKwetter.model.Models.Details;
import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;
import webappKwetter.model.Models.User;

import java.util.List;

public class MockProfileContext implements IProfileContext {
    @Override
    public List<Kweet> getTimeLine(Profile profile) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void hearthKweet(Kweet kweet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void postKweet(String content) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void followProfile(Profile profile) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createProfile(User owner, Details detail) {
        throw new UnsupportedOperationException();
    }
}
