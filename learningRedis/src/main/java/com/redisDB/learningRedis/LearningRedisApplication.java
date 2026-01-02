package com.redisDB.learningRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LearningRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningRedisApplication.class, args);
	}

}
