package com.fontys.kweetservice;

import com.fontys.kweetservice.config.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
public class KweetServiceApplication{

    public static void main(String[] args) {
        SpringApplication.run(KweetServiceApplication.class, args);

    }

}
