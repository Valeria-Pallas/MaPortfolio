package com.example.Mediatheque_TP4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages="com.example")
@EnableJpaRepositories("com.example.Repository")
@EntityScan("com.example.Models")   
public class MediathequeTp4Application {

	public static void main(String[] args) {
		SpringApplication.run(MediathequeTp4Application.class, args);
	}

}
