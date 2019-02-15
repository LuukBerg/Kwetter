package webappKwetter.dao.IContext;

import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;

import java.util.List;

public interface IKweetContext {
    public Kweet create(Kweet kweet);
    public Kweet findKweetById(long id);
    public List<Kweet> findByProfile(long id);
    public List<Kweet> getTimeLine(Profile profile);

}
