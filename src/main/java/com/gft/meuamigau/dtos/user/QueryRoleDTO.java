package com.gft.meuamigau.dtos.user;

import java.util.List;

import com.gft.meuamigau.enums.RoleName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryRoleDTO {
	
	private Long id;
	
	private RoleName name;
	
	private List<QueryUserDTO> users;

}
