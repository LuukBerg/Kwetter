package com.fontys.javabatch.config;

import java.util.List;

import com.fontys.javabatch.Tweet;
import com.fontys.javabatch.doa.TweetDoa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final TweetDoa tweetDoa;

    @Autowired
    public JobCompletionNotificationListener(TweetDoa tweetDoa) {
        this.tweetDoa = tweetDoa;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        RowMapper rowMapper = (rs, rowNum) -> {
            Tweet tweet = new Tweet();
            tweet.setScreenName(rs.getString(1));
            tweet.setTweet(rs.getString(2));
            tweet.setPostedFrom(rs.getString(3));
            tweet.setPostdate(rs.getDate(4));
            return tweet;
        };
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            List<Tweet> tweetList = tweetDoa.findAll();

            log.info("Size of List "+tweetList.size());
            for (Tweet tweet: tweetList) {
                log.info("Found: "+ tweet.toString());

            }
        }
    }
}