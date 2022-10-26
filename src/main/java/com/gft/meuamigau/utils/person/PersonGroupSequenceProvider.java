package com.gft.meuamigau.utils.person;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.gft.meuamigau.dtos.person.RecordPersonDTO;

public class PersonGroupSequenceProvider implements DefaultGroupSequenceProvider<RecordPersonDTO>{

	@Override
	public List<Class<?>> getValidationGroups(RecordPersonDTO person) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(RecordPersonDTO.class);
		
		if (isSelectedPerson(person))
			groups.add(person.getPersonType().getGroupClass());
		
		return groups;
	
	}
	
	protected boolean isSelectedPerson(RecordPersonDTO person) {
		
		return person != null && person.getPersonType() != null;
	
	}
	
}
