package com.pran.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pran.models.Pet;

@Component
public class PetDetailsServiceProxyFallback implements PetDetailsServiceProxy {

	@Override
	public String home() {
		String message = "Response from fallback class";
		return message;
	}

	@Override
	public ResponseEntity<List<Pet>> getPets() {
		List<Pet> list = new ArrayList<>();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Pet> getPetById(int id) {
		Pet petModel = new Pet();
		return new ResponseEntity<>(petModel, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Pet> addPet(Pet pet) {
		Pet petModel = new Pet();
		return new ResponseEntity<>(petModel, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Pet> updatePet(int id, Pet pet) {
		Pet petModel = new Pet();
		return new ResponseEntity<>(petModel, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deletePet(int id) {
		return new ResponseEntity<>("Something went wrong", HttpStatus.OK);
	}
	
}
