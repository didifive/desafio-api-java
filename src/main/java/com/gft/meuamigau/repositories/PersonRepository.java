package com.gft.meuamigau.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.meuamigau.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	boolean existsByCpfCnpj(String cpfCnpj);
	
	boolean existsByEmailIgnoreCase(String email);
	
	boolean existsByUserId(Long userId);

}
