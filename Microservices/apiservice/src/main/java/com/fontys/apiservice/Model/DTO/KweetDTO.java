package com.fontys.apiservice.Model.DTO;


import com.fontys.apiservice.Model.Kweet;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class KweetDTO implements Serializable {
    private long id;
    private long ownerId;
    private String content;
    private String owner;
    private Date date;

    public KweetDTO(String content) {
        this.content = content;
    }

    public static List<KweetDTO> transform(List<Kweet> kweets) {
        List<KweetDTO> kweetDTOS = new ArrayList<>();
        for (Kweet kweet : kweets) {
            kweetDTOS.add(new KweetDTO(kweet.getId(), kweet.getOwner().getId(), kweet.getContent(), kweet.getOwner().getOwner().getUsername(), kweet.getDate()));
        }
        return kweetDTOS;
    }

    public static KweetDTO transform(Kweet kweet) {
        return new KweetDTO(kweet.getId(), kweet.getOwner().getId(), kweet.getContent(), kweet.getOwner().getOwner().getUsername(), kweet.getDate());
    }

}
