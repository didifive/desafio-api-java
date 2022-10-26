package com.gft.meuamigau.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.meuamigau.entities.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	
	Page<Attendance> findByClientId(Long clientId, Pageable pageable);
	
	Page<Attendance> findByVetId(Long vetId, Pageable pageable);
	
	Page<Attendance> findByDogId(Long dogId, Pageable pageable);

}
