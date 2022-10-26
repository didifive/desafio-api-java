package com.gft.meuamigau.config.populate;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gft.meuamigau.dtos.attendance.QueryAttendanceDTO;
import com.gft.meuamigau.dtos.attendance.RecordAttendanceDTO;
import com.gft.meuamigau.services.AttendanceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AttendancePopulate {	
	
	private static final String DIAGNOSIS = "Doggo ipsum doing me a frighten heck fluffer yapper what a nice floof";
	private static final String COMMENTS = "Doggo ipsum doing me a frighten heck fluffer yapper what a nice floof, "
			+ "such treat very good spot doggo. Maximum borkdrive smol borking doggo with a long snoot for pats shibe "
			+ "very good spot wow very biscit blep, blop yapper heckin angery woofer waggy wags. Borking doggo maximum "
			+ "borkdrive wow such tempt very hand that feed shibe, stop it fren. Heckin snoot you are doin me a concern, "
			+ "dat tungg tho. maximum borkdrive. Shooberino lotsa pats ur givin me a spook long woofer, blop. What a nice "
			+ "floof ruff extremely cuuuuuute noodle horse heckin good boys wow very biscit, wow such tempt long water "
			+ "shoob shoober."
			+ "qui officia deserunt mollit anim id est laborum.";
	private static final String DATE_TIME_ATTENDANCE = "28/07/2022 09:30:20";
	private static final String DATE_TIME_ATTENDANCE_2 = "28/07/2022 18:30:20";
	
	private final AttendanceService attendanceService;
	
	private static final RecordAttendanceDTO ATTENDANCE_1 = RecordAttendanceDTO.builder()
			.dogId(1L)
			.vetId(1L)
			.attendanceDate(DATE_TIME_ATTENDANCE)
			.dogHeight(80)
			.dogWeight(10.5)
			.diagnosis(DIAGNOSIS)
			.comments(COMMENTS)
			.temperament("Muito brabo")
			.build();
	
	private static final RecordAttendanceDTO ATTENDANCE_2 = RecordAttendanceDTO.builder()
			.dogId(1L)
			.vetId(2L)
			.attendanceDate(DATE_TIME_ATTENDANCE_2)
			.dogHeight(100)
			.dogWeight(20)
			.diagnosis(DIAGNOSIS)
			.temperament("Muito chato")
			.build();
	
	private static final RecordAttendanceDTO ATTENDANCE_3 = RecordAttendanceDTO.builder()
			.dogId(2L)
			.vetId(1L)
			.dogHeight(80)
			.dogWeight(10.5)
			.diagnosis(DIAGNOSIS)
			.comments(COMMENTS)
			.temperament("Muito legal")
			.build();
	
	private static final RecordAttendanceDTO ATTENDANCE_4 = RecordAttendanceDTO.builder()
			.dogId(3L)
			.vetId(1L)
			.attendanceDate(DATE_TIME_ATTENDANCE)
			.dogHeight(80)
			.dogWeight(10.5)
			.diagnosis(DIAGNOSIS)
			.comments(COMMENTS)
			.temperament("Muito brabo")
			.build();
	
	private static final RecordAttendanceDTO ATTENDANCE_5 = RecordAttendanceDTO.builder()
			.dogId(4L)
			.vetId(1L)
			.attendanceDate(DATE_TIME_ATTENDANCE)
			.dogHeight(80)
			.dogWeight(10.5)
			.diagnosis(DIAGNOSIS)
			.comments(COMMENTS)
			.build();
	
	

	public void createAttendances() {
			
		Pageable pageable = PageRequest.of(0, 10);
		Page<QueryAttendanceDTO> attendanceList = attendanceService.findAll(pageable);
		
		if(attendanceList.getContent().isEmpty()) {
			List<RecordAttendanceDTO> attendances = List.of(ATTENDANCE_1, ATTENDANCE_2, ATTENDANCE_3, ATTENDANCE_4, ATTENDANCE_5);
			attendances.forEach(attendanceService::create);
		}
		
    }
}