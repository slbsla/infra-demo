package com.infra.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class InfraDemoApplication extends SpringBootServletInitializer {

    // Nécessaire pour déploiement WAR sur Liberty Core
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(InfraDemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(InfraDemoApplication.class, args);
    }
}
