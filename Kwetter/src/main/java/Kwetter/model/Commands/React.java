package Kwetter.model.Commands;


import Kwetter.model.Models.Profile;
import Kwetter.model.Models.Kweet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class React extends Command {
    @Id
    @GeneratedValue
    private long id;
    private String content;
    @ManyToOne
    private Profile owner;

    public React() {
    }

    public React(String content, Profile owner, Kweet kweet) {
        this.content = content;
        this.owner = owner;
        action(kweet);
    }

    @Override
    public void action(Kweet kweet) {
        Command command = this;
        kweet.getCommands().add(command);
    }
}
