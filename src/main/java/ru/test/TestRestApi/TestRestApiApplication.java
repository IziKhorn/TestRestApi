package ru.test.TestRestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.UUID;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TestRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestRestApiApplication.class, args);
		System.out.println(UUID.randomUUID());
	}

}
