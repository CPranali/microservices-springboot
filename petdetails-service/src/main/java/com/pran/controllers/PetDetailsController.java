package com.pran.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
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
import com.pran.services.PetDetailsService;

@RestController
public class PetDetailsController {

	Logger logger = LoggerFactory.getLogger(PetDetailsController.class);

	@Autowired
	private PetDetailsService petDetailsService;

	@Autowired
	private Environment env;

	@GetMapping("/")
	public String home() {
		return "Hello from Pet Detail service running at port:" + env.getProperty("local.server.port");
	}

	@GetMapping(path = "/pets" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pet>> getPets() {
		logger.info("Entering in getPets ");
		List<Pet> result = petDetailsService.getPetDetails();
		logger.info("Exiting from getPets ");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(path = "/pet/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pet> getPetById(@PathVariable int id) {
		logger.info("Entering in getPetById with Id: " + id);
		Pet result = petDetailsService.getPetDetailsById(id);
		logger.info("Exiting from getPetById");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(path = "/addpet", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
		logger.info("Entering in addPet ");
		Pet newPet = petDetailsService.addPet(pet);
		return new ResponseEntity<>(newPet, HttpStatus.OK);
	}

	@PutMapping(path = "/updatepet/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody Pet pet) {
		logger.info("Entering in update pet ");
		Pet newPet = petDetailsService.updatePetById(id, pet);
		return new ResponseEntity<>(newPet, HttpStatus.OK);
	}

	@DeleteMapping("/deletepet/{id}")
	public ResponseEntity<String> deletePet(@PathVariable int id) {
		logger.info("Entering in update pet ");
		petDetailsService.deleteById(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
}
