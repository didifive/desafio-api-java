package com.gft.meuamigau.dtos.vet;

import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;

import com.gft.meuamigau.dtos.person.PersonMapper;
import com.gft.meuamigau.entities.Person;
import com.gft.meuamigau.entities.Vet;
import com.gft.meuamigau.utils.DateTimeConverter;

public class VetMapper {
	
	private VetMapper() {
		throw new IllegalAccessError("Utility Class");
	}
	
	public static Vet toEntity(RecordVetDTO dto) {
		Vet vet = new Vet();
		vet.setSpeciality(dto.getSpeciality());
		vet.setCrmv(dto.getCrmv());
		vet.setCrmvUf(dto.getCrmvUf());
		if(dto.getRegistrationDate() != null)
			vet.setRegistrationDate(DateTimeConverter.stringtoDate(dto.getRegistrationDate(), DATE_PATTERN));
		vet.setPerson(new Person(dto.getPersonId()));
		
		return vet;
	}	
	
	public static QueryVetDTO toQueryDTO(Vet vet) {
		return QueryVetDTO.builder()
				.id(vet.getId())
				.registrationDate(DateTimeConverter.dateToString(vet.getRegistrationDate(), DATE_PATTERN))
				.speciality(vet.getSpeciality())
				.crmv(vet.getCrmv())
				.crmvUf(vet.getCrmvUf())
				.person(PersonMapper.toAbstractDTO(vet.getPerson()))
				.username(vet.getPerson().getUser().getUsername())
				.build();
	}
	
	public static AbstractVetDTO toAbstractDTO(Vet vet) {
		AbstractVetDTO abstractVetDTO = AbstractVetDTO.builder()
				.id(vet.getId())
				.registrationDate(DateTimeConverter.dateToString(vet.getRegistrationDate(), DATE_PATTERN))
				.speciality(vet.getSpeciality())
				.crmv(vet.getCrmv())
				.crmvUf(vet.getCrmvUf())
				.build();
		
		if(vet.getPerson() !=null)
			abstractVetDTO.setName(vet.getPerson().getName());
		
		return abstractVetDTO;
	}	
	
}
