package kwetter.model.DTO;

import java.io.Serializable;

public class KweetDTO implements Serializable {
    private long id;
    private long ownerId;
    private String content;

    public KweetDTO() {
    }

    public KweetDTO(long id, long ownerId, String content) {
        this.id = id;
        this.ownerId = ownerId;
        this.content = content;
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
}
