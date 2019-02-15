package Models.Commands;

import Models.Kweet;
import Models.Profile;

public class Hearth implements Command {
    private Profile owner;

    public Hearth(Profile owner, Kweet kweet) {
        this.owner = owner;
        action(kweet);
    }

    @Override
    public void action(Kweet kweet) {
        kweet.getCommands().add(this);
    }
}
