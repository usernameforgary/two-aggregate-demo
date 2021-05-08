package com.example.demo;

import org.axonframework.modelling.command.Repository;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Autowired
    private AxonConfiguration axonConfiguration;

//    @Bean
//    public Repository<School> schoolRepository2() {
//        return axonConfiguration.repository(School.class);
//    }

}
