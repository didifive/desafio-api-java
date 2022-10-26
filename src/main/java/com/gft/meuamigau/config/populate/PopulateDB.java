package com.gft.meuamigau.config.populate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PopulateDB implements CommandLineRunner{
	
	private final UserPopulate userPopulate;
	private final PersonPopulate personPopulate;
	private final ClientVetPopulate clientVetPopulate;
	private final BreedDogPopulate breedDogPopulate;
	private final AttendancePopulate attendancePopulate;

	@Override
	public void run(String... args) throws Exception {
		userPopulate.createRolesAndUsers();
		personPopulate.createPersons();
		clientVetPopulate.createClients();
		clientVetPopulate.createVets();
		breedDogPopulate.createBreed();
		breedDogPopulate.createDogs();
		attendancePopulate.createAttendances();
	}

}
