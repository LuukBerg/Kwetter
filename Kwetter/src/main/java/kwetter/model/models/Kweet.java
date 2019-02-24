package kwetter.model.models;

import kwetter.model.commands.Hearth;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Kweet implements Comparable<Kweet>, Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String content;
    private Date date;
    @ManyToOne
    private Profile owner;
    //TODO add hearths to jpa
    @Transient
    private List<Hearth> hearths;

    public Kweet() {
    }

    public Kweet(String content, Profile owner) {
        this.content = content;
        this.date = new Date();
        hearths = new ArrayList<>();
        this.owner = owner;
    }


    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Hearth> getHearths() {
        return hearths;
    }
    public void addHearht(Hearth hearth){
        hearths.add(hearth);
    }
    public void removeHearth(Hearth hearth){
        if(hearths.contains(hearth)){
            hearths.remove(hearth);
        }
    }

    public long getId() {
        return id;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Kweet kweet) {
        return date.compareTo(kweet.getDate());
    }
}
