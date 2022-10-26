package com.gft.meuamigau.utils;

import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;

import java.util.Date;
import java.util.List;

import com.gft.meuamigau.dtos.person.PersonMapper;
import com.gft.meuamigau.dtos.vet.AbstractVetDTO;
import com.gft.meuamigau.dtos.vet.QueryVetDTO;
import com.gft.meuamigau.dtos.vet.RecordVetDTO;
import com.gft.meuamigau.entities.Attendance;
import com.gft.meuamigau.entities.Person;
import com.gft.meuamigau.entities.Vet;

public class VetUtils {
	
	public static final Long ID = 1L;
	public static final String REGISTRATION_DATE_STRING = "13/12/1990";
	public static final Date REGISTRATION_DATE = DateTimeConverter.stringtoDate(REGISTRATION_DATE_STRING, DATE_PATTERN);
	public static final Person PERSON = PersonUtils.createFakeEntity();
	public static final String SPECIALITY = "Clínico Geral";
	public static final String CRMV = "45671891/342";
	public static final String CRMV_UF = "SP";
	public static final Attendance ATTENDANCE = new Attendance(ID);
	public static final List<Attendance> ATTENDANCES = List.of(ATTENDANCE);
	
	
	public static Vet createFakeEntity() {
		return new Vet(
					ID
					, REGISTRATION_DATE
					, SPECIALITY
					, CRMV
					, CRMV_UF
					, PERSON
					, ATTENDANCES
				);
	}
	
	public static RecordVetDTO createFakeRecordVetDTO() {
		return RecordVetDTO.builder()
				.personId(PERSON.getId())
				.registrationDate(REGISTRATION_DATE_STRING)
				.speciality(SPECIALITY)
				.crmv(CRMV)
				.crmvUf(CRMV_UF)
				.build();
	}
	
	public static QueryVetDTO createFakeQueryVetDTO() {
		return QueryVetDTO.builder()
				.id(ID)
				.registrationDate(REGISTRATION_DATE_STRING)
				.speciality(SPECIALITY)
				.crmv(CRMV)
				.crmvUf(CRMV_UF)
				.person(PersonMapper.toAbstractDTO(PERSON))
				.username(PERSON.getUser().getUsername())
				.build();
	}
	
	public static AbstractVetDTO createFakeAbstractVetDTO() {
		return AbstractVetDTO.builder()
				.id(ID)
				.registrationDate(REGISTRATION_DATE_STRING)
				.speciality(SPECIALITY)
				.crmv(CRMV)
				.crmvUf(CRMV_UF)
				.build();
	}
	
}
