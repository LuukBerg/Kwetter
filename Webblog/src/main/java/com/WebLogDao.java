package com;

import java.util.List;

public interface WebLogDao {
    void addPosting(Posting p);
    List<Posting> getPostings();

}
