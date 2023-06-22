package com.example.springmemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  //jpa auditing을 사용하기 위한 애너테이션
@SpringBootApplication
public class SpringMemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMemoApplication.class, args);
    }

}
