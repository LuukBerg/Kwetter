package com.fontys.profiledoa.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;




@Configuration
@EnableConfigurationProperties
@Getter
@Primary
@Setter
@ToString
public class ServerProperties {

    private String environment;
    private List<String> hosts = new ArrayList<>();

}

