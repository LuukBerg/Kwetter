package Models.Commands;

import Models.Kweet;
import Models.Profile;

public class React implements Command{

    private String content;
    private Profile owner;
    public React(String content, Profile owner, Kweet kweet) {
        this.content = content;
        this.owner = owner;
        action(kweet);
    }

    @Override
    public void action(Kweet kweet) {
        kweet.getCommands().add(this);
    }
}
