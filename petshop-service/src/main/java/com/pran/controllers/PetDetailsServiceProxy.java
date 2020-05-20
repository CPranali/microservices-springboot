package com.pran.controllers;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pran.models.Pet;

@FeignClient(name="petdetails-service", fallback=PetDetailsServiceProxyFallback.class)
//@RibbonClient(name="petdetails-service")
public interface PetDetailsServiceProxy {
	
	@GetMapping("/")
	public String home();
	
	@GetMapping("/pets")  
	public ResponseEntity<List<Pet>> getPets();
	
	@GetMapping("/pet/{id}")
	public ResponseEntity<Pet> getPetById(@PathVariable(value="id") int id);
	
	@PostMapping("/addpet")
	public ResponseEntity<Pet> addPet(@RequestBody Pet pet);
	
	@PutMapping("/updatepet/{id}")
	public ResponseEntity<Pet> updatePet(@PathVariable(value="id") int id, @RequestBody Pet pet);
	
	@DeleteMapping("/deletepet/{id}")
	public ResponseEntity<String> deletePet(@PathVariable(value="id") int id) ;
}
