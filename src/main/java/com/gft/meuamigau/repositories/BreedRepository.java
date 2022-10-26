package com.gft.meuamigau.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.meuamigau.entities.Breed;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {

}
