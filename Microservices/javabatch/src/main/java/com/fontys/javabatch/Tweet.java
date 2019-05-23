package com.fontys.javabatch;


import lombok.*;
import org.hibernate.annotations.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Tweet implements Serializable{

    @Id
    @GeneratedValue
    private long id;
    private String screenName;
    private String tweet;
    private String postedFrom;
    private Date postdate;
//{"screenName":"Frank","tweet":"Zo, de eerste #freakyfriday zit erop. Mooie resultaten op #fhict http://t.co/YiimzrL4U4","postedFrom":"Android","postDate":"2014-01-17 01:30:51"}




}
