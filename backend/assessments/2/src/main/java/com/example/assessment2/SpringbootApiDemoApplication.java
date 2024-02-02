package com.example.assessment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example")
@EnableJpaRepositories("com.example.assessment2.repository")
@EntityScan("com.example.assessment2.model")
public class SpringbootApiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApiDemoApplication.class, args);
    }
}
