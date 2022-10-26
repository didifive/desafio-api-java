package com.gft.meuamigau.utils;

import static com.gft.meuamigau.utils.DateTimeConverter.DATE_TIME_PATTERN;

import java.util.Date;

import com.gft.meuamigau.dtos.attendance.QueryAttendanceDTO;
import com.gft.meuamigau.dtos.attendance.RecordAttendanceDTO;
import com.gft.meuamigau.dtos.client.ClientMapper;
import com.gft.meuamigau.dtos.dog.DogMapper;
import com.gft.meuamigau.dtos.vet.VetMapper;
import com.gft.meuamigau.entities.Attendance;
import com.gft.meuamigau.entities.Client;
import com.gft.meuamigau.entities.Dog;
import com.gft.meuamigau.entities.Vet;

public class AttendanceUtils {
	
	public static final Long ID = 1L;
	public static final Dog DOG = DogUtils.createFakeEntity();
	public static final Client CLIENT = ClientUtils.createFakeEntity();
	public static final Vet VET = VetUtils.createFakeEntity();
	public static final String DATE_STRING = "15/01/2022 15:30:21";
	public static final Date DATE = DateTimeConverter.stringtoDate(DATE_STRING, DATE_TIME_PATTERN);
	public static final String DIAGNOSIS = "Diagnóstico aqui";
	public static final String COMMENTS = "Comentários aqui";
	public static final double DOG_WEIGHT = 20.5;
	public static final double DOG_HEIGHT = 120.8;
	public static final String DOG_TEMPERAMENT = "Temperamento aqui";
	
	public static Attendance createFakeEntity() {
		Attendance attendance = new Attendance();
		attendance.setId(ID);
		attendance.setDog(DOG);
		attendance.setClient(CLIENT);
		attendance.setVet(VET);
		attendance.setAttendanceDate(DATE);
		attendance.setDiagnosis(DIAGNOSIS);
		attendance.setComments(COMMENTS);
		attendance.setDogWeight(DOG_WEIGHT);
		attendance.setDogHeight(DOG_HEIGHT);
		attendance.setDogTemperament(DOG_TEMPERAMENT);
		return attendance;
	}
	
	public static RecordAttendanceDTO createFakeRecordAttendanceDTO() {
		return RecordAttendanceDTO.builder()
				.dogId(DOG.getId())
				.vetId(VET.getId())
				.attendanceDate(DATE_STRING)
				.diagnosis(DIAGNOSIS)
				.comments(COMMENTS)
				.dogWeight(DOG_WEIGHT)
				.dogHeight(DOG_HEIGHT)
				.temperament(DOG_TEMPERAMENT)
				.build();
	}
	
	public static QueryAttendanceDTO createFakeQueryAttendanceDTO() {
		return QueryAttendanceDTO.builder()
				.id(ID)
				.dog(DogMapper.toQueryDTO(DOG))
				.client(ClientMapper.toAbstractDTO(CLIENT))
				.vet(VetMapper.toAbstractDTO(VET))
				.attendanceDate(DATE_STRING)
				.diagnosis(DIAGNOSIS)
				.comments(COMMENTS)
				.dogWeight(DOG_WEIGHT)
				.dogHeight(DOG_HEIGHT)
				.temperament(DOG_TEMPERAMENT)
				.build();
	}
	
}
