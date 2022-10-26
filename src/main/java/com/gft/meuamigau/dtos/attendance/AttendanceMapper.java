package com.gft.meuamigau.dtos.attendance;

import static com.gft.meuamigau.utils.DateTimeConverter.DATE_TIME_PATTERN;

import com.gft.meuamigau.dtos.client.ClientMapper;
import com.gft.meuamigau.dtos.dog.DogMapper;
import com.gft.meuamigau.dtos.vet.VetMapper;
import com.gft.meuamigau.entities.Attendance;
import com.gft.meuamigau.entities.Client;
import com.gft.meuamigau.entities.Dog;
import com.gft.meuamigau.entities.Vet;
import com.gft.meuamigau.utils.DateTimeConverter;

public class AttendanceMapper {
	
	private AttendanceMapper() {
		throw new IllegalAccessError("Utility Class");
	}
	
	/**
	 * Convert RecordAttendanceDTO to Attendance
	 * @param RecordAttendanceDTO dto
	 * @return Attendance
	 */
	public static Attendance toEntity(RecordAttendanceDTO dto) {
		
		Attendance attendance = new Attendance();
		attendance.setDog(new Dog(dto.getDogId()));
		attendance.setClient(new Client());
		attendance.setVet(new Vet(dto.getVetId()));
		if (dto.getAttendanceDate() != null)
			attendance.setAttendanceDate(DateTimeConverter.stringtoDate(dto.getAttendanceDate(), DATE_TIME_PATTERN));
		attendance.setDiagnosis(dto.getDiagnosis());
		attendance.setComments(dto.getComments());
		attendance.setDogWeight(dto.getDogWeight());
		attendance.setDogHeight(dto.getDogHeight());
		attendance.setDogTemperament(dto.getTemperament());	
		
		return attendance;
		
	}	
	
	/**
	 * Convert Attendance to QueryAttendanceDTO
	 * @param Attendance attendance
	 * @return QueryAttendanceDTO
	 */
	public static QueryAttendanceDTO toQueryDTO(Attendance attendance) {
				
		return QueryAttendanceDTO.builder()
				.id(attendance.getId())
				.dog(DogMapper.toQueryDTO(attendance.getDog()))
				.client(ClientMapper.toAbstractDTO(attendance.getClient()))
				.vet(VetMapper.toAbstractDTO(attendance.getVet()))
				.attendanceDate(DateTimeConverter.dateToString(attendance.getAttendanceDate(), DATE_TIME_PATTERN))
				.diagnosis(attendance.getDiagnosis())
				.comments(attendance.getComments())
				.dogWeight(attendance.getDogWeight())
				.dogHeight(attendance.getDogHeight())
				.temperament(attendance.getDogTemperament())
				.build();
	}
	
}
