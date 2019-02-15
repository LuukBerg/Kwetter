package Models.Commands;

import Models.Kweet;

public interface Command {

    void action(Kweet kweet);

}
