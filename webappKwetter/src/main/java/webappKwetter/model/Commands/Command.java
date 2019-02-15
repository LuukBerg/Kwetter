package Models.Commands;

import webappKwetter.model.Models.Kweet;

public interface Command {

    void action(Kweet kweet);

}
