package com.example.java_play_ground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JavaPlayGroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaPlayGroundApplication.class, args);
	}

}
