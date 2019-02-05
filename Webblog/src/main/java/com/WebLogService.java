package com;

import java.util.List;

public class WebLogService {
    WebLogDao context;

    public WebLogService(WebLogDao context) {
        this.context = context;
    }

    public void addPosting(Posting p){
        context.addPosting(p);
    }
    public List<Posting> getPosting(){
        return context.getPostings();
    }
}
