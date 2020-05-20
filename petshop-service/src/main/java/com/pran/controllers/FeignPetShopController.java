package com.pran.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pran.models.Pet;

@RestController
public class FeignPetShopController {

	Logger logger = LoggerFactory.getLogger(FeignPetShopController.class);
	
	@Autowired
	private PetDetailsServiceProxy petDetailsServiceProxy;

	@Autowired
	private Environment env;

	@GetMapping("/")
	public String home() {
		return "Hello from Pet Shop service running at port:" + env.getProperty("local.server.port");
	}

	@GetMapping("/petdetails")
	public String homePetDetails() {
		return petDetailsServiceProxy.home();
	}

	@GetMapping(path = "/allpets",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pet>> getAllPets() {
		logger.info("Calling getPets method");
		return petDetailsServiceProxy.getPets();
	}

	@GetMapping(path = "/pet/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pet> getPetById(@PathVariable int id) {
		logger.info("Calling getPetById with id: "+ id);
		return petDetailsServiceProxy.getPetById(id);

	}
	
	@PostMapping(path = "/addpet", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
		logger.info("Entering in addPet ");
		return petDetailsServiceProxy.addPet(pet);
	}

	@PutMapping(path = "/updatepet/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody Pet pet) {
		logger.info("Entering in update pet ");
		return petDetailsServiceProxy.updatePet(id, pet);
	}

	@DeleteMapping("/deletepet/{id}")
	public ResponseEntity<String> deletePet(@PathVariable int id) {
		logger.info("Entering in delete pet ");
		return petDetailsServiceProxy.deletePet(id);
	}

}
