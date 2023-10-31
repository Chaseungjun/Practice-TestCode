package com.kiosk.testcodepractice.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TestCodePracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCodePracticeApplication.class, args);
    }

}
