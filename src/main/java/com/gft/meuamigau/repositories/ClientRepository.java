package com.gft.meuamigau.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.meuamigau.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	boolean existsByPersonId(Long personId);
	
}
