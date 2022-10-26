package com.gft.meuamigau.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.meuamigau.entities.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
	
	Page<Dog> findByClientId(Long clientId, Pageable pageable);

}
