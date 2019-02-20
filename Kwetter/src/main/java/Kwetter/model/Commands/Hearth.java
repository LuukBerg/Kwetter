package Kwetter.model.Commands;

import Kwetter.model.Models.Profile;
import Kwetter.model.Models.Kweet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


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
