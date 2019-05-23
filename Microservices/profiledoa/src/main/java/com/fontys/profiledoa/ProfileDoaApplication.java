package com.fontys.profiledoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.fontys.profiledoa.repository")
@SpringBootApplication
public class ProfileDoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileDoaApplication.class, args);
    }

}
