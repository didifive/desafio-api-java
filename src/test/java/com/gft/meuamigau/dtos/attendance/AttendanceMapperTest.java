package com.gft.meuamigau.dtos.attendance;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gft.meuamigau.entities.Attendance;
import com.gft.meuamigau.utils.AttendanceUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test DTO Mappers -> AttendanceMapper")
class AttendanceMapperTest {
	
	private Attendance attendance;
	private RecordAttendanceDTO recordAttendanceDTO;
	private QueryAttendanceDTO queryAttendanceDTO;
		
	@BeforeEach
	private void setup() {
		attendance = AttendanceUtils.createFakeEntity();
		recordAttendanceDTO = AttendanceUtils.createFakeRecordAttendanceDTO();
		queryAttendanceDTO = AttendanceUtils.createFakeQueryAttendanceDTO();
	}
	
	@Test
	@DisplayName("0. O construtor padrão deve estar privado")
	void defaultConstructorShouldBePrivate() throws Exception {
		Constructor<AttendanceMapper> constructor = AttendanceMapper.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}
	
	@Test
	@DisplayName("1. Deve converter RecordAttendanceDTO para Attendance")
	void shouldConvertRecordAttendanceDTOToAttendance()throws Exception {
		//then
		Attendance result = AttendanceMapper.toEntity(recordAttendanceDTO);
		assertAll("Assert who expected Attendance is returned"
				,() -> assertEquals(attendance.getDog().getId(), 	result.getDog().getId())
				,() -> assertEquals(attendance.getVet().getId(), 	result.getVet().getId())
				,() -> assertEquals(attendance.getAttendanceDate(), result.getAttendanceDate())
				,() -> assertEquals(attendance.getDiagnosis(), 		result.getDiagnosis())
				,() -> assertEquals(attendance.getComments(), 		result.getComments())
				,() -> assertEquals(attendance.getDogWeight(), 		result.getDogWeight())
				,() -> assertEquals(attendance.getDogHeight(), 		result.getDogHeight())
				,() -> assertEquals(attendance.getDogTemperament(), result.getDogTemperament())
		);
	}
	
	@Test
	@DisplayName("2. Deve converter Attendance para QueryAttendanceDTO")
	void shouldConvertAttendanceToQueryAttendanceDTO()throws Exception {
		//then
		QueryAttendanceDTO result = AttendanceMapper.toQueryDTO(attendance);
		assertAll("Assert who expected QueryAttendanceDTO is returned"
				,() -> assertEquals(queryAttendanceDTO.getId(),  			result.getId())
				,() -> assertEquals(queryAttendanceDTO.getDog(),  			result.getDog())
				,() -> assertEquals(queryAttendanceDTO.getClient(), 		result.getClient())
				,() -> assertEquals(queryAttendanceDTO.getVet(), 			result.getVet())
				,() -> assertEquals(queryAttendanceDTO.getAttendanceDate(),	result.getAttendanceDate())
				,() -> assertEquals(queryAttendanceDTO.getDiagnosis(), 		result.getDiagnosis())
				,() -> assertEquals(queryAttendanceDTO.getComments(), 		result.getComments())
				,() -> assertEquals(queryAttendanceDTO.getDogWeight(), 		result.getDogWeight())
				,() -> assertEquals(queryAttendanceDTO.getDogHeight(), 		result.getDogHeight())
				,() -> assertEquals(queryAttendanceDTO.getTemperament(), 	result.getTemperament())

		);
	}

}
