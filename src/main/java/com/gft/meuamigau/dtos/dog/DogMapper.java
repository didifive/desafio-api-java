package com.gft.meuamigau.dtos.dog;

import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;

import com.gft.meuamigau.dtos.breed.BreedMapper;
import com.gft.meuamigau.dtos.client.ClientMapper;
import com.gft.meuamigau.entities.Breed;
import com.gft.meuamigau.entities.Client;
import com.gft.meuamigau.entities.Dog;
import com.gft.meuamigau.utils.DateTimeConverter;

public class DogMapper {
	
	private DogMapper() {
		throw new IllegalAccessError("Utility Class");
	}
	
	/**
	 * Convert RecordDogDTO to Dog
	 * @param RecordDogDTO dto
	 * @return Dog
	 */
	public static Dog toEntity(RecordDogDTO dto) {
		
		Dog dog = new Dog();
		dog.setName(dto.getName());
		dog.setBreed(new Breed(dto.getBreedId()));
		dog.setColor(dto.getColor());
		dog.setClient(new Client(dto.getClientId()));
		
		if (dto.getRegistrationDate() != null)
			dog.setRegistrationDate(DateTimeConverter.stringtoDate(dto.getRegistrationDate(), DATE_PATTERN));
		
		if (dto.getBirthDate() != null)
			dog.setBirthDate(DateTimeConverter.stringtoDate(dto.getBirthDate(), DATE_PATTERN));
		
		return dog;
		
	}	
	
	/**
	 * Convert Dog to QueryDogDTO
	 * @param Dog dog
	 * @return QueryDogDTO
	 */
	public static QueryDogDTO toQueryDTO(Dog dog) {
				
		QueryDogDTO queryDogDTO = QueryDogDTO.builder()
				.id(dog.getId())
				.name(dog.getName())
				.breed(BreedMapper.toQueryDTO(dog.getBreed()))
				.color(dog.getColor())
				.client(ClientMapper.toAbstractDTO(dog.getClient()))
				.build();
		
		if (dog.getBirthDate() != null)
			queryDogDTO.setBirthDate(DateTimeConverter.dateToString(dog.getBirthDate(), DATE_PATTERN));
		
		if (dog.getRegistrationDate() != null)
			queryDogDTO.setRegistrationDate(DateTimeConverter.dateToString(dog.getRegistrationDate(), DATE_PATTERN));
		
		return queryDogDTO;
	}
	
	/**
	 * Convert Dog to AbstractDogDTO
	 * @param Dog dog
	 * @return AbstractDogDTO
	 */
	public static AbstractDogDTO toAbstractDTO(Dog dog) {
		AbstractDogDTO abstractDogDTO = AbstractDogDTO.builder()
				.id(dog.getId())
				.name(dog.getName())
				.color(dog.getColor())
				.breedName(dog.getBreed().getName())
				.build();
		
		if (dog.getBirthDate() != null)
			abstractDogDTO.setBirthDate(DateTimeConverter.dateToString(dog.getBirthDate(), DATE_PATTERN));
		
		return abstractDogDTO;
	}	
	
}
