package webappKwetter.model.Commands;

import webappKwetter.model.Models.Kweet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Command {
    @Id
    @GeneratedValue
    private long id;
    void action(Kweet kweet){

    };

}
