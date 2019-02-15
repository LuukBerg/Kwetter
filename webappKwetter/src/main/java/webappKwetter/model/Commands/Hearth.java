package webappKwetter.model.Commands;

import webappKwetter.model.Models.Kweet;
import webappKwetter.model.Models.Profile;

import javax.persistence.*;

@Entity
public class Hearth extends Command {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Profile owner;

    public Hearth() {
    }

    public Hearth(Profile owner, Kweet kweet) {
        this.owner = owner;
        action(kweet);
    }

    @Override
    public void action(Kweet kweet) {
        kweet.getCommands().add(this);
    }
}
