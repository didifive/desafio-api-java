package com.gft.meuamigau.utils;

import static com.gft.meuamigau.enums.RoleName.ROLE_ADMIN;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gft.meuamigau.dtos.person.PersonMapper;
import com.gft.meuamigau.dtos.user.QueryUserDTO;
import com.gft.meuamigau.dtos.user.RecordUserDTO;
import com.gft.meuamigau.entities.Attendance;
import com.gft.meuamigau.entities.Role;
import com.gft.meuamigau.entities.User;
import com.gft.meuamigau.enums.RoleName;

public class UserUtils {
	
	public static final Long ID = 1L;
	public static final String USERNAME = "Admin";
	public static final String PASSWORD = "123456";
	public static final Set<Role> ROLES = Set.of(new Role(ROLE_ADMIN));
	public static final List<RoleName> ROLES_NAMES = List.of(ROLE_ADMIN);
	public static final Attendance ATTENDANCE = new Attendance(ID);
	public static final List<Attendance> ATTENDANCES = List.of(ATTENDANCE);
	
	
	public static User createFakeEntity() {
		return new User(
					ID
					, USERNAME
					, PASSWORD
					, Set.of(RoleUtils.createFakeEntity())
					, PersonUtils.createFakeEntity()
					, new ArrayList<>()
				);
	}
	
	public static RecordUserDTO createFakeRecordUserDTO() {
		return RecordUserDTO.builder()
				.username(USERNAME)
				.password(PASSWORD)
				.rolesNames(ROLES_NAMES)
				.build();
	}
	
	public static QueryUserDTO createFakeQueryUserDTO() {
		return QueryUserDTO.builder()
				.id(ID)
				.username(USERNAME)
				.rolesNames(ROLES_NAMES)
				.person(PersonMapper.toAbstractDTO(PersonUtils.createFakeEntity()))
				.build();
	}
	
}
