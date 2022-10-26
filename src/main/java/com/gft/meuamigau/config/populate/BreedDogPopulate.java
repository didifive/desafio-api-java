package com.gft.meuamigau.config.populate;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gft.meuamigau.dtos.breed.RequestBreedDTO;
import com.gft.meuamigau.dtos.dog.QueryDogDTO;
import com.gft.meuamigau.dtos.dog.RecordDogDTO;
import com.gft.meuamigau.repositories.BreedRepository;
import com.gft.meuamigau.services.BreedService;
import com.gft.meuamigau.services.DogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BreedDogPopulate {	
	
	private static final String DATE_REGISTRATION = "28/07/2022";
	private static final String DATE_BIRTH = "20/01/2020";
	
	private final BreedService breedService;
	private final BreedRepository breedRepository;
	
	private final DogService dogService;
	
	private static final RequestBreedDTO BREED_999999999 = RequestBreedDTO.builder()
			.id(999999999L)
            .name("Undefined Breed")
            .breedGroup("Pooch")
            .origin("Darwin")
			.build();
	
	private static final RecordDogDTO DOG_1 = RecordDogDTO.builder()
			.birthDate(DATE_BIRTH)
			.breedId(999999999L)
			.clientId(1L)
			.color("Caramelo")
			.name("Vira Lata Caramelo")
			.registrationDate(DATE_REGISTRATION)
			.build();
	
	private static final RecordDogDTO DOG_2 = RecordDogDTO.builder()
			.breedId(149L)
			.clientId(2L)
			.color("Preto")
			.name("Kitana")
			.registrationDate(DATE_REGISTRATION)
			.build();
	
	private static final RecordDogDTO DOG_3 = RecordDogDTO.builder()
			.breedId(1L)
			.clientId(2L)
			.color("Cinza")
			.name("Cerberus")
			.build();
	
	private static final RecordDogDTO DOG_4 = RecordDogDTO.builder()
			.birthDate(DATE_BIRTH)
			.breedId(7L)
			.clientId(3L)
			.color("Branco")
			.name("SubZero")
			.registrationDate(DATE_REGISTRATION)
			.build();
	
	

	public void createBreed() {
		
		if(breedRepository.findById(999999999L).isEmpty()) {
			breedService.create(BREED_999999999);
		}
		
    }
	
	public void createDogs() {
			
		Pageable pageable = PageRequest.of(0, 10);
		Page<QueryDogDTO> queryDog = dogService.findAll(pageable);
		
		if(queryDog.getContent().isEmpty()) {
			List<RecordDogDTO> listaDogs = List.of(DOG_1, DOG_2, DOG_3, DOG_4);
			listaDogs.forEach(dogService::create);
		}
		
    }
}