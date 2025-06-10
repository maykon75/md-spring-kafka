package com.kafka.project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger"))
public class MdSpringKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdSpringKafkaApplication.class, args);
	}

}
