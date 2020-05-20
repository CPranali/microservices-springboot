package com.pran;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableSwagger2
public class PetShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplateBean() {
		System.out.println("Rest template bean===========");
		return new RestTemplate();
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.pran.controllers"))
				.paths(PathSelectors.any())
				.build().apiInfo(new ApiInfo("PetShop Service API Documentation", "This service is used for pet shop", "1.0.0", null, 
						new Contact("Pranali Chakole","", "chakolepranalis@gmail,com"), null, null, new ArrayList()));
	}
	
}
