package com.gft.meuamigau.utils;

import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gft.meuamigau.dtos.breed.QueryBreedDTO;
import com.gft.meuamigau.dtos.client.AbstractClientDTO;
import com.gft.meuamigau.dtos.client.ClientMapper;
import com.gft.meuamigau.dtos.dog.AbstractDogDTO;
import com.gft.meuamigau.dtos.dog.QueryDogDTO;
import com.gft.meuamigau.dtos.dog.RecordDogDTO;
import com.gft.meuamigau.entities.Attendance;
import com.gft.meuamigau.entities.Breed;
import com.gft.meuamigau.entities.Client;
import com.gft.meuamigau.entities.Dog;

public class DogUtils {
	
	public static final Long ID = 1L;
	public static final String NAME = "Kitana";
	public static final Breed BREED = new Breed();
	public static final QueryBreedDTO QUERY_BREED = new QueryBreedDTO();
	public static final String COLOR = "Caramelo";
	public static final String DATE_STRING = "13/12/2005";
	public static final Date DATE = DateTimeConverter.stringtoDate(DATE_STRING, DATE_PATTERN);
	public static final Client CLIENT = new Client(
			ID
			, DateTimeConverter.stringtoDate(DATE_STRING, DateTimeConverter.DATE_PATTERN)
			, PersonUtils.createFakeEntity()
			, new ArrayList<>()
			, new ArrayList<>());
	public static final AbstractClientDTO ABSTRACT_CLIENT_DTO = ClientMapper.toAbstractDTO(CLIENT);
	public static final Attendance ATTENDANCE = new Attendance(ID);
	public static final List<Attendance> ATTENDANCES = List.of(ATTENDANCE);
	
	
	public static Dog createFakeEntity() {
		Dog dog = new Dog();
		dog.setId(ID);
		dog.setName(NAME);
		dog.setBreed(BREED);
		dog.setColor(COLOR);
		dog.setBirthDate(DATE);
		dog.setRegistrationDate(DATE);
		dog.setClient(CLIENT);
		dog.setAttendances(ATTENDANCES);
		return dog;
	}
	
	public static RecordDogDTO createFakeRecordDogDTO() {
		return RecordDogDTO.builder()
				.name(NAME)
				.breedId(ID)
				.color(COLOR)
				.birthDate(DATE_STRING)
				.registrationDate(DATE_STRING)
				.clientId(ID)
				.build();
	}
	
	public static QueryDogDTO createFakeQueryDogDTO() {
		return QueryDogDTO.builder()
				.id(ID)
				.name(NAME)
				.breed(QUERY_BREED)
				.color(COLOR)
				.birthDate(DATE_STRING)
				.registrationDate(DATE_STRING)
				.client(ABSTRACT_CLIENT_DTO)
				.build();
	}
	
	public static AbstractDogDTO createFakeAbstractDogDTO() {
		return AbstractDogDTO.builder()
				.id(ID)
				.name(NAME)
				.breedName(QUERY_BREED.getName())
				.color(COLOR)
				.birthDate(DATE_STRING)
				.build();
	}
	
}
