package webappKwetter.model.Models;

import webappKwetter.model.Commands.Command;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Kweet {
    @Id
    @GeneratedValue
    private long id;
    private String content;
    private Date date;
    @ManyToOne
    private Profile owner;
    @Transient
    private List<Command> commands;

    public Kweet() {
    }

    public Kweet(String content, Profile owner) {
        this.content = content;
        this.date = new Date();
        commands = new ArrayList<>();
        this.owner = owner;
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

    public long getId() {
        return id;
    }

    public Profile getOwner() {
        return owner;
    }
}
