package com.example.jpahw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.jpahw.repository")
@EntityScan("com.example.jpahw.model")
@SpringBootApplication
public class JpahwApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpahwApplication.class, args);
	}
}
