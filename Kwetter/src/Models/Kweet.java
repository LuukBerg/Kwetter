package Models;

import java.util.Date;

public class Kweet {
    private Long id;
    private String content;
    private Date date;

    public Kweet() {
    }

    public Kweet(String content) {
        this.content = content;
        this.date = new Date();
    }


    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

}
