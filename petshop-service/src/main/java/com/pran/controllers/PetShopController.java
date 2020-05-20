package com.pran.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pran.models.Pet;

@RestController
@RequestMapping("/rest")
public class PetShopController {
	
	Logger logger = LoggerFactory.getLogger(PetShopController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	
	public String home() {
		return "Hello from Pet Shop service running at port:" + env.getProperty("local.server.port");
	}

	@GetMapping("/petdetails")
	public ResponseEntity<String> homePetDetails() {
		String petsResult = restTemplate.getForObject("http://PETDETAILS-SERVICE/", String.class);

		return new ResponseEntity<>(petsResult, HttpStatus.OK);
	}

	@GetMapping("/allpets")
	public ResponseEntity<List> getAllPets() {
		logger.info("Calling getPets method");
		String url = "http://PETDETAILS-SERVICE/pets";
		List petsResult = restTemplate.getForObject(url, List.class);

		return new ResponseEntity<>(petsResult, HttpStatus.OK);
	}

	@GetMapping("/pet/{id}")
	public Pet getPetById(@PathVariable int id) {
		logger.info("Calling getPetById with id: "+ id);
		String url = "http://PETDETAILS-SERVICE/pet/" + id;
		Pet petsResult = restTemplate.getForObject(url, Pet.class);

		return petsResult;
	}

}
