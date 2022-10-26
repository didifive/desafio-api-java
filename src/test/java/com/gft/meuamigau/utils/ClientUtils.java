package com.gft.meuamigau.utils;

import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;

import java.util.Date;
import java.util.List;

import com.gft.meuamigau.dtos.client.AbstractClientDTO;
import com.gft.meuamigau.dtos.client.QueryClientDTO;
import com.gft.meuamigau.dtos.client.RecordClientDTO;
import com.gft.meuamigau.dtos.dog.DogMapper;
import com.gft.meuamigau.dtos.person.PersonMapper;
import com.gft.meuamigau.entities.Attendance;
import com.gft.meuamigau.entities.Client;
import com.gft.meuamigau.entities.Dog;
import com.gft.meuamigau.entities.Person;

public class ClientUtils {
	
	public static final Long ID = 1L;
	public static final String REGISTRATION_DATE_STRING = "13/12/1990";
	public static final Date REGISTRATION_DATE = DateTimeConverter.stringtoDate(REGISTRATION_DATE_STRING, DATE_PATTERN);
	public static final Person PERSON = PersonUtils.createFakeEntity();
	public static final Dog DOG = DogUtils.createFakeEntity();
	public static final List<Dog> DOGS = List.of(DOG);
	public static final Attendance ATTENDANCE = new Attendance(ID);
	public static final List<Attendance> ATTENDANCES = List.of(ATTENDANCE);
	
	
	public static Client createFakeEntity() {
		return new Client(
					ID
					, REGISTRATION_DATE
					, PERSON
					, DOGS
					, ATTENDANCES
				);
	}
	
	public static RecordClientDTO createFakeRecordClientDTO() {
		return RecordClientDTO.builder()
				.personId(PERSON.getId())
				.registrationDate(REGISTRATION_DATE_STRING)
				.build();
	}
	
	public static QueryClientDTO createFakeQueryClientDTO() {
		return QueryClientDTO.builder()
				.id(ID)
				.registrationDate(REGISTRATION_DATE_STRING)
				.person(PersonMapper.toAbstractDTO(PERSON))
				.username(PERSON.getUser().getUsername())
				.dogs(List.of(DogMapper.toAbstractDTO(DOG)))
				.build();
	}
	
	public static AbstractClientDTO createFakeAbstractClientDTO() {
		return AbstractClientDTO.builder()
				.id(ID)
				.registrationDate(REGISTRATION_DATE_STRING)
				.build();
	}
	
}
