package kwetter.dao.icontext;

import kwetter.model.models.Profile;
import kwetter.model.models.Kweet;

import java.util.List;

public interface IKweetContext {
    public Kweet create(Kweet kweet);
    public Kweet findKweetById(long id);
    public List<Kweet> findByProfile(long id);
    public List<Kweet> getTimeLine(Profile profile);

    List<Kweet> getAllOrderedByDate();

    void deleteById(Long id);
    Kweet update(Kweet kweet);

}
