package com.henrique.banc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@EnableJdbcAuditing
@SpringBootApplication
public class BancApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancApplication.class, args);
    }

}
