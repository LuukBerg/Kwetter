package fontys.com.kwetterdoa.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Kweet implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Size(max = 140, message = "kweet cannot be longer then 140 characters")
    private String content;
    private Date date;
    @ManyToOne
    private Profile owner;
    //TODO add hearths to jpa


    public Kweet(String content, Profile owner) {
        this.content = content;
        this.date = new Date();
        this.owner = owner;
        //owner.addKweet(this);
    }


}
