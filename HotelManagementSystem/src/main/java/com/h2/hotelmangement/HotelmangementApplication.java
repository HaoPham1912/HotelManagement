package com.h2.hotelmangement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HotelmangementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelmangementApplication.class, args);
    }

}
