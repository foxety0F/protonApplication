package com.foxety0f.proton.modules.roles.dao;

import java.util.List;

import com.foxety0f.proton.common.exceptions.UserNotFound;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.roles.domain.ProtonRole;
import com.foxety0f.proton.modules.roles.domain.ProtonUserRole;

public interface IRoleDAO {

	List<ProtonRole> getProtonRoles();
	
	List<ProtonUserRole> getProtonRoleUser();
	
	List<ProtonUserRole> getProtonRoleUser(String userName) throws UserNotFound;

	void createNewRole(String roleName, UserDetailsProton user);
	
	void createNewRole(String roleName);
	
	void addRoleToUser(Integer roleId, Integer userId, UserDetailsProton user);
	
	void removeRoleFromUser(Integer roleId, Integer userId, UserDetailsProton user);
}
