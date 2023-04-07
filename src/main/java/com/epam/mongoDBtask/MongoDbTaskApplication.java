package com.epam.mongoDBtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class MongoDbTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbTaskApplication.class, args);
	}

}
