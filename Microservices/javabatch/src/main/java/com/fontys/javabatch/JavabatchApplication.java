package com.fontys.javabatch;

import com.fontys.javabatch.config.BatchConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class JavabatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchConfiguration.class, args);
    }

}
