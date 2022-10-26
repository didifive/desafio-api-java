package com.gft.meuamigau.dtos.client;

import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;

import java.util.List;

import com.gft.meuamigau.dtos.dog.AbstractDogDTO;
import com.gft.meuamigau.dtos.dog.DogMapper;
import com.gft.meuamigau.dtos.person.PersonMapper;
import com.gft.meuamigau.entities.Client;
import com.gft.meuamigau.entities.Person;
import com.gft.meuamigau.utils.DateTimeConverter;

public class ClientMapper {
	
	private ClientMapper() {
		throw new IllegalAccessError("Utility Class");
	}
	
	public static Client toEntity(RecordClientDTO dto) {
		
		Client client= new Client();
		client.setPerson(new Person(dto.getPersonId()));
		if(dto.getRegistrationDate() != null)
			client.setRegistrationDate(DateTimeConverter.stringtoDate(dto.getRegistrationDate(), DATE_PATTERN));		

		return client;
	}	
	

	public static QueryClientDTO toQueryDTO(Client client) {
			
		List<AbstractDogDTO> dogs = client.getDogs().stream()
    		.map(DogMapper::toAbstractDTO)
    		.toList();
			
		return QueryClientDTO.builder()
				.id(client.getId())
				.registrationDate(DateTimeConverter.dateToString(client.getRegistrationDate(), DATE_PATTERN))
				.person(PersonMapper.toAbstractDTO(client.getPerson()))
				.username(client.getPerson().getUser().getUsername())
				.dogs(dogs)
				.build();
	}
	
	public static AbstractClientDTO toAbstractDTO(Client client) {
		AbstractClientDTO abstractClientDTO = AbstractClientDTO.builder()
				.id(client.getId())
				.registrationDate(DateTimeConverter.dateToString(client.getRegistrationDate(), DATE_PATTERN))
				.build();
		if (client.getPerson() != null)
			abstractClientDTO.setName(client.getPerson().getName());
		
		return abstractClientDTO;
	}	
	
}
