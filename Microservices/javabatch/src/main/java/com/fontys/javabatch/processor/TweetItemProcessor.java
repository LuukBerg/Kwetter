package com.fontys.javabatch.processor;

import com.fontys.javabatch.Tweet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;


public class TweetItemProcessor implements ItemProcessor<Tweet, Tweet> {

    private static final Logger log = LoggerFactory.getLogger(TweetItemProcessor.class);

    @Override
    public Tweet process(Tweet tweet) throws Exception {
      //todo jpa?
        log.info("persist " + tweet.toString());
       return tweet;
    }
}
