package com;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Posting{
    public String title;
    private String content;
    private Date date;
    private List<Comment> comments;

    public Posting(String title, String content, Date date, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.comments = comments;
    }
    public Posting(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.comments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public List<Comment> getComments() {
        return comments;
    }

}
