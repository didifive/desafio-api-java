package com.gft.meuamigau.dtos.person;

import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;

import java.util.ArrayList;
import java.util.List;

import com.gft.meuamigau.dtos.address.AddressMapper;
import com.gft.meuamigau.dtos.address.QueryAddressDTO;
import com.gft.meuamigau.dtos.client.ClientMapper;
import com.gft.meuamigau.dtos.vet.VetMapper;
import com.gft.meuamigau.entities.Address;
import com.gft.meuamigau.entities.Person;
import com.gft.meuamigau.utils.DateTimeConverter;

public class PersonMapper {
	
	private PersonMapper() {
		throw new IllegalAccessError("Utility Class");
	}
	
	/**
	 * Convert RecordPersonDTO to Person
	 * @param RecordPersonDTO dto
	 * @return Person
	 */
	public static Person toEntity(RecordPersonDTO dto) {
		
		List<Address> addresses = new ArrayList<>();
		if(dto.getAddresses() != null) {
			addresses = dto.getAddresses().stream()
    				.map(AddressMapper::fromDTO)
    				.toList();
		}
		
		Person person = new Person();
		person.setName(dto.getName());
		person.setPersonType(dto.getPersonType());
		person.setCpfCnpj(dto.getCpfCnpj());
		person.setBirthDate(DateTimeConverter.stringtoDate(dto.getBirthDate(), DATE_PATTERN));
		person.setPhone(dto.getPhone());
		person.setEmail(dto.getEmail());
		person.setAddresses(addresses);
		
		return person;
		
	}	
	
	/**
	 * Convert Person to QueryPersonDTO
	 * @param Person person
	 * @return QueryPersonDTO
	 */
	public static QueryPersonDTO toQueryDTO(Person person) {
		
		List<QueryAddressDTO> addresses = person.getAddresses().stream()
				.map(AddressMapper::fromEntity)
				.toList();
				
		return QueryPersonDTO.builder()
				.id(person.getId())
				.name(person.getName())
				.personType(person.getPersonType())
				.cpfCnpj(person.getCpfCnpj())
				.email(person.getEmail())
				.birthDate(DateTimeConverter.dateToString(person.getBirthDate(), DATE_PATTERN))
				.phone(person.getPhone())
				.addresses(addresses)
				.username(person.getUser().getUsername())
				.build();
	}
	
	/**
	 * Convert Person to DetailsPersonDTO
	 * @param Person person
	 * @return DetailsPersonDTO
	 */
	public static DetailsPersonDTO toDetailsDTO(Person person) {
		
		List<QueryAddressDTO> addresses = person.getAddresses().stream()
				.map(AddressMapper::fromEntity)
				.toList();
		
		DetailsPersonDTO detailsPersonDTO = DetailsPersonDTO.builder()
                                				.id(person.getId())
                                				.name(person.getName())
                                				.personType(person.getPersonType())
                                				.cpfCnpj(person.getCpfCnpj())
                                				.email(person.getEmail())
                                				.birthDate(DateTimeConverter.dateToString(person.getBirthDate(), DATE_PATTERN))
                                				.phone(person.getPhone())
                                				.addresses(addresses)
                                				.username(person.getUser().getUsername())
                                				.build();
		
		if (person.getClient() != null)
			detailsPersonDTO.setClient(ClientMapper.toAbstractDTO(person.getClient()));
		
		if (person.getVet() != null)
			detailsPersonDTO.setVet(VetMapper.toAbstractDTO(person.getVet()));
		
		return detailsPersonDTO;
	}	
	
	/**
	 * Convert Person to AbstractPersonDTO
	 * @param Person person
	 * @return AbstractPersonDTO
	 */
	public static AbstractPersonDTO toAbstractDTO(Person person) {
		
		return AbstractPersonDTO.builder()
				.id(person.getId())
				.name(person.getName())
				.personType(person.getPersonType())
				.email(person.getEmail())
				.build();
	}	
}
