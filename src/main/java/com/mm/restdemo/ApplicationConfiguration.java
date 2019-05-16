package com.mm.restdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;

@Configuration
@PropertySource("classpath:config.properties")
public class ApplicationConfiguration {

    @Value("${groupList}")
    private List<String> groupList;

    @Value("${group1Limit}")
    private String group1Limit;

    @Value("${group2Limit}")
    private String group2Limit;

    @Value("${group3Limit}")
    private String group3Limit;

    public List<String> getGroupList() {
        return groupList;
    }

    public String getGroup1Limit() {
        return group1Limit;
    }

    public String getGroup2Limit() {
        return group2Limit;
    }

    public String getGroup3Limit() {
        return group3Limit;
    }

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
