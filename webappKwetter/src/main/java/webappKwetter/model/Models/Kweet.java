package webappKwetter.model.Models;

import Models.Commands.Command;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kweet {
    private Long id;
    private String content;
    private Date date;
    private List<Command> commands;

    public Kweet() {
    }

    public Kweet(String content) {
        this.content = content;
        this.date = new Date();
        commands = new ArrayList<>();
    }


    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
