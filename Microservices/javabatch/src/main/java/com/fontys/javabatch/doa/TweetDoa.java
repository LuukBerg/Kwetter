package com.fontys.javabatch.doa;

import com.fontys.javabatch.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetDoa extends JpaRepository<Tweet, Long> {
}
