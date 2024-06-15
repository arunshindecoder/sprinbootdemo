package org.arun.spring.sprinbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class SprinbootdemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SprinbootdemoApplication.class, args);
    }

}
