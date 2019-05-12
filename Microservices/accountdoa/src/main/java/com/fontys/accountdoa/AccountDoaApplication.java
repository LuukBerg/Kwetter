package com.fontys.accountdoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.fontys.accountdoa.Repository")
@SpringBootApplication
public class AccountDoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountDoaApplication.class, args);
    }

}
