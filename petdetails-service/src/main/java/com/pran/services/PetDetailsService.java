package com.pran.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pran.exception.PetDetailsException;
import com.pran.models.Pet;
import com.pran.repository.PetDetailsRepository;

@Service
public class PetDetailsService {

	Logger logger = LoggerFactory.getLogger(PetDetailsService.class);

	@Autowired
	private PetDetailsRepository petDeatilsRepository;

	public List<Pet> getPetDetails() {
		logger.info("In getPetDetails ");
		return petDeatilsRepository.findAll();
	}

	public Pet getPetDetailsById(int id) {
		logger.info("Entering getPetDetailsById with Id " + id);
		return petDeatilsRepository.findById(id);
	}

	public Pet addPet(Pet pet) {
		logger.info("In addPet");
		return petDeatilsRepository.add(pet);
	}

	public Pet updatePetById(int id, Pet pet) {
		logger.info("In addPet");
		return petDeatilsRepository.updatePetById(id, pet);
	}

	public void deleteById(int id) {
		logger.info("In deleteById");
		Pet pet = this.getPetDetailsById(id);
		if (pet.getCount() <= 1)
			petDeatilsRepository.deleteById(id);
		else
			throw new PetDetailsException("Unable to delete pet as count is not 1");
	}

}
