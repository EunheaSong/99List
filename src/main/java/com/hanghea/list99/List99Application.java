package com.hanghea.list99;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class List99Application {

    public static void main(String[] args) {
        SpringApplication.run(List99Application.class, args);
    }

}
