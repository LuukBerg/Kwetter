package kwetter.dao.icontext;

import kwetter.model.models.Profile;

public interface IProfileContext {
    Profile update(Profile entity);
    Profile create(Profile entity);
    Profile findbyId(long id);
    void deleteById(long id);
}
