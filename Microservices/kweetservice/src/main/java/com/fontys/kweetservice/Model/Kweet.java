package com.fontys.kweetservice.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class Kweet implements Serializable {

    private long id;
    @Size(max = 140, message = "kweet cannot be longer then 140 characters")
    private String content;
    private Date date;

    private Profile owner;
    //TODO add hearths to jpa


    public Kweet(String content, Profile owner) {
        this.content = content;
        this.date = new Date();
        this.owner = owner;
        owner.addKweet(this);
    }


}
