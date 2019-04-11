package kwetter.model.DTO;

import kwetter.model.models.Kweet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KweetDTO implements Serializable {
    private long id;
    private long ownerId;
    private String content;
    private String owner;
    private Date date;

    public KweetDTO() {
    }

    public KweetDTO(String content) {
        this.content = content;
    }

    public KweetDTO(long id, long ownerId, String content, String owner, Date date) {
        this.id = id;
        this.ownerId = ownerId;
        this.content = content;
        this.owner = owner;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    //long id, long ownerId, String content, String owner, Date date
    //TODO better solution?
    public static List<KweetDTO> transform(List<Kweet> kweets){
        List<KweetDTO> kweetDTOS = new ArrayList<>();
        for(Kweet kweet : kweets){
            kweetDTOS.add(new KweetDTO(kweet.getId(),kweet.getOwner().getId(),kweet.getContent(),kweet.getOwner().getOwner().getUsername(),kweet.getDate()));
        }
        return kweetDTOS;
    }
    public static KweetDTO transform(Kweet kweet){
        return new KweetDTO(kweet.getId(),kweet.getOwner().getId(),kweet.getContent(),kweet.getOwner().getOwner().getUsername(),kweet.getDate());

    }
}
