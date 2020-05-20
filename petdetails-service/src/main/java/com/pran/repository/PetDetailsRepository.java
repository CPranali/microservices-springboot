package com.pran.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pran.exception.PetNotFoundException;
import com.pran.models.Pet;

public class PetDetailsRepository {
	
	private List<Pet> pets = new ArrayList<>();

	public Pet add(Pet pet) {
		pet.setId(pets.size() + 1);
		pets.add(pet);
		return pet;
	}
	
	public Pet findById(int id) {
		Optional<Pet> pet = pets.stream().filter(e-> e.getId() == id).findFirst();
		if(pet.isPresent()) 
			return pet.get();
		else
			throw new PetNotFoundException("Pet not found with id: "+id);
	}
	
	public List<Pet> findAll(){
		return pets;
	}
	
	public List<Pet> findByType(String type){
		return pets.stream().filter(e-> e.getType().equals(type) ).collect(Collectors.toList());
	}
	
	public Pet updatePetById(int id, Pet p) {
		Optional<Pet> pet = pets.stream().filter(e-> e.getId() == id).findFirst();
		if(pet.isPresent()) {
			Pet oldPet = pet.get();
			oldPet.setPrice(p.getPrice());
			oldPet.setCount(p.getCount());
			oldPet.setSize(p.getSize());
			return oldPet;
		}else {
			throw new PetNotFoundException("Pet not found with id: "+id);
		}
	}
	
	public void deleteById(int id) {
		Optional<Pet> pet = pets.stream().filter(e-> e.getId() == id).findFirst();
		if(pet.isPresent()) 
			 pets.remove(pet.get());
	}
}
