package com;

import java.util.*;

public class WebLogDaoImp implements WebLogDao {

    List<Posting> postings;
    public WebLogDaoImp() {
        postings = new ArrayList<>();
        postings.add(new Posting("title1","content1", new Date(1549212718)));
        postings.add(new Posting("title2","content2", new Date()));
    }

    @Override
    public void addPosting(Posting p) {
        postings.add(p);
    }

    @Override
    public List<Posting> getPostings() {

        return postings;
    }
}
