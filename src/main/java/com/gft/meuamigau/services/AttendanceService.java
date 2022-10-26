package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.constants.ErrorMessages.ATENDIMENTO_NAO_ENCONTRADO_BY_ID;
import static com.gft.meuamigau.utils.DateTimeConverter.DATE_TIME_PATTERN;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gft.meuamigau.dtos.attendance.AttendanceMapper;
import com.gft.meuamigau.dtos.attendance.QueryAttendanceDTO;
import com.gft.meuamigau.dtos.attendance.RecordAttendanceDTO;
import com.gft.meuamigau.entities.Attendance;
import com.gft.meuamigau.entities.User;
import com.gft.meuamigau.enums.EntityDtoBoxType;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.AttendanceRepository;
import com.gft.meuamigau.utils.DateTimeConverter;
import com.gft.meuamigau.utils.EntityDtoBox;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AttendanceService {

	private final AttendanceRepository attendanceRepository;
	
	private final ClientService clientService;
	
	private final VetService vetService;
	
	private final DogService dogService;
	
	/**
	 * CRUD: Read
	 * Find attendance by id and returns EntityDtoBox<Attendance, QueryAttendanceDTO>
	 * @param Long id
	 * @return EntityDtoBox<Attendance, QueryAttendanceDTO>
	 */
	public EntityDtoBox<Attendance, QueryAttendanceDTO> findById(Long id, EntityDtoBoxType returnType) {
		Attendance attendance = attendanceRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(
						String.format(ATENDIMENTO_NAO_ENCONTRADO_BY_ID, id)));
		if(returnType.equals(ENTITY)){
			return new EntityDtoBox<>(attendance,returnType);
		}
		return new EntityDtoBox<>(AttendanceMapper.toQueryDTO(attendance),returnType);
	}
	public EntityDtoBox<Attendance, QueryAttendanceDTO> findById(Long id) {
		return findById(id, DTO);
	}
	
	/**
	 * CRUD: Read
	 * Find all attendances in database and return content with pagination
	 * @param Pageable pageable
	 * @return Page<QueryAttendanceDTO>
	 */
	public Page<QueryAttendanceDTO> findAll(Pageable pageable) {
		Page<Attendance> attendancePage = attendanceRepository.findAll(pageable);
		
		return transformPageOfAttendancesInPageQueryAttendanceDTO(attendancePage, pageable);
	}
	
	/**
	 * CRUD: Read
	 * Find all attendances by client in database and return content with pagination
	 * @param Pageable pageable
	 * @return Page<QueryAttendanceDTO>
	 */
	public Page<QueryAttendanceDTO> findAllByClientId(Long clientId, Pageable pageable) {
		Page<Attendance> attendancePage = attendanceRepository.findByClientId(clientId, pageable);
		
		return transformPageOfAttendancesInPageQueryAttendanceDTO(attendancePage, pageable);
	}
	
	/**
	 * CRUD: Read
	 * Find all attendances by vet in database and return content with pagination
	 * @param Pageable pageable
	 * @return Page<QueryAttendanceDTO>
	 */
	public Page<QueryAttendanceDTO> findAllByVetId(Long vetId, Pageable pageable) {
		Page<Attendance> attendancePage = attendanceRepository.findByVetId(vetId, pageable);
		
		return transformPageOfAttendancesInPageQueryAttendanceDTO(attendancePage, pageable);
	}
	
	/**
	 * CRUD: Read
	 * Find all attendances by dog in database and return content with pagination
	 * @param Pageable pageable
	 * @return Page<QueryAttendanceDTO>
	 */
	public Page<QueryAttendanceDTO> findAllByDogId(Long dogId, Pageable pageable) {
		Page<Attendance> attendancePage = attendanceRepository.findByDogId(dogId, pageable);
		
		return transformPageOfAttendancesInPageQueryAttendanceDTO(attendancePage, pageable);
	}

	/**
	 * CRUD: Create
	 * Create attendance and persist in database for respective dog, client and breed
	 * @param RecordAttendanceDTO givenAttendanceDTO
	 * @return QueryAttendanceDTO
	 */
	public QueryAttendanceDTO create(RecordAttendanceDTO givenAttendanceDTO) {
		
		Attendance attendance = AttendanceMapper.toEntity(givenAttendanceDTO);
		
		if (givenAttendanceDTO.getAttendanceDate() == null) {
			attendance.setAttendanceDate(DateTimeConverter.dataHoraAtual());
		}
		
		setDogClientAndVet(attendance);
		
		Attendance savedAttendance = attendanceRepository.save(attendance);
		
		return AttendanceMapper.toQueryDTO(savedAttendance);
	}
	
	/**
	 * CRUD: Update
	 * Update existing attendance
	 * @param RecordAttendanceDTO givenAttendanceDTO
	 * @return QueryAttendanceDTO
	 */
	public QueryAttendanceDTO update(Long id, RecordAttendanceDTO givenAttendanceDTO) {
		
		Attendance existingAttendance = this.findById(id, ENTITY).getEntity();
		
		Attendance currentAttendance = AttendanceMapper.toEntity(givenAttendanceDTO);
		currentAttendance.setId(id);
		setDogClientAndVet(currentAttendance);
		if (givenAttendanceDTO.getAttendanceDate() != null) {
			currentAttendance.setAttendanceDate(
					DateTimeConverter.stringtoDate(
							givenAttendanceDTO.getAttendanceDate(), DATE_TIME_PATTERN));
		} else {
			currentAttendance.setAttendanceDate(existingAttendance.getAttendanceDate());
		}
		
		Attendance updatedAttendance = attendanceRepository.save(currentAttendance);
		
		return AttendanceMapper.toQueryDTO(updatedAttendance);
	}
	

	/**
	 * CRUD: Delete
	 * Delete attendance
	 * @param Long id
	 * @return void
	 */
	public void deleteById(Long id) {
		this.findById(id);
					
		attendanceRepository.deleteById(id);
	}
	
	
	private void setDogClientAndVet(Attendance attendance) {
		attendance.setDog(dogService.findById(attendance.getDog().getId(),ENTITY).getEntity());
		attendance.setClient(clientService.findById(attendance.getDog().getClient().getId(),ENTITY).getEntity());
		attendance.setVet(vetService.findById(attendance.getVet().getId(), ENTITY).getEntity());
		
		User user;
		if(SecurityContextHolder.getContext().getAuthentication() != null)
			user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		else {
			user = attendance.getVet().getPerson().getUser();
		}
		attendance.setUser(user);
	}
	
	private Page<QueryAttendanceDTO> transformPageOfAttendancesInPageQueryAttendanceDTO(
			Page<Attendance> attendancePage
			, Pageable pageable) {
		List<QueryAttendanceDTO> dtos = attendancePage.getContent().stream()
										.map(AttendanceMapper::toQueryDTO)
										.toList();
		 
		return new PageImpl<>(dtos, pageable, attendancePage.getTotalElements());
	}
}
