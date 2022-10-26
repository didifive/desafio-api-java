package com.gft.meuamigau.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.meuamigau.entities.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long>{

	boolean existsByPersonId(Long personId);
	
}
