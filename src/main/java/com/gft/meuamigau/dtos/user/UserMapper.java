package com.gft.meuamigau.dtos.user;

import java.util.ArrayList;
import java.util.HashSet;

import com.gft.meuamigau.dtos.person.PersonMapper;
import com.gft.meuamigau.entities.Role;
import com.gft.meuamigau.entities.User;

public class UserMapper {
	
	private UserMapper() {
		throw new IllegalAccessError("Utility Class");
	}
	

	/**
	 * Convert RecordUserDTO to User
	 * @param RecordUserDTO dto
	 * @return User
	 */
	public static User fromDTO(RecordUserDTO dto) {	
		User user =  new User(dto.getUsername()
				, dto.getPassword()
				, new HashSet<>());
		
		if (!dto.getRolesNames().isEmpty())
			dto.getRolesNames().forEach(roleName -> user.getRoles().add(new Role(roleName)));
			
		return user;
	}
	
	/**
	 * Convert User to QueryUserDTO
	 * @param User user
	 * @return QueryUserDTO
	 */
	public static QueryUserDTO fromEntity(User user) {
		
		QueryUserDTO queryUserDTO = new QueryUserDTO(
				user.getId()
				, user.getUsername()
				, new ArrayList<>()
				, null);
		
		if (user.getPerson() != null) 
			queryUserDTO.setPerson(PersonMapper.toAbstractDTO(user.getPerson()));
		
		if (!user.getRoles().isEmpty())
			user.getRoles().forEach(role -> queryUserDTO.getRolesNames().add(role.getName()));
		
		return queryUserDTO;
	}
	
}
