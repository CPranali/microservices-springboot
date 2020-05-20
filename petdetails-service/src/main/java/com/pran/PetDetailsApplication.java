package com.pran;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.pran.models.Pet;
import com.pran.repository.PetDetailsRepository;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class PetDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetDetailsApplication.class, args);
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.pran.controllers"))
				.paths(PathSelectors.any())
				.build().apiInfo(new ApiInfo("PetDetails Service API Documentation", "This is for fetching pet details", "1.0.0", null, 
						new Contact("Pranali Chakole", null, "chakolepranalis@gmail,com"), null, null, new ArrayList()));
	}
	
	@Bean
	public PetDetailsRepository petDetailsRepository() {
		PetDetailsRepository repository = new PetDetailsRepository();
		repository.add(new Pet(1,"Dog", 2, 100.00, "Small"));
		repository.add(new Pet(2,"Cat", 1, 200.00, "Big"));
		repository.add(new Pet(3,"Rabbit", 2, 400.00, "Medium"));
		repository.add(new Pet(4,"Parrot", 5, 300.00, "Medium"));
		repository.add(new Pet(5,"Dog", 3, 400.00, "Big"));
		repository.add(new Pet(6,"Cat", 2, 100.00, "Small"));
		
		return repository;
	}
}
