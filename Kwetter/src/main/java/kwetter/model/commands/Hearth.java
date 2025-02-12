package kwetter.model.commands;

import kwetter.model.models.Profile;
import kwetter.model.models.Kweet;

import javax.persistence.*;


@Entity
public class Hearth{
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Profile owner;

    @ManyToOne
    private Kweet kweet;
    public Hearth() {
    }

    public Hearth(Profile owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }
}
