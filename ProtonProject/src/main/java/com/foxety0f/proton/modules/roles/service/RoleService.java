package com.foxety0f.proton.modules.roles.service;

import java.util.List;

import com.foxety0f.proton.common.exceptions.UserNotFound;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.roles.dao.IRoleDAO;
import com.foxety0f.proton.modules.roles.domain.ProtonRole;
import com.foxety0f.proton.modules.roles.domain.ProtonUserRole;

public class RoleService implements IRoleService {

	private IRoleDAO roleDao;

	private ProtonModules thisModule = ProtonModules.ROLES;

	public RoleService(IRoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	public List<ProtonRole> getProtonRoles() {
		return roleDao.getProtonRoles();
	}

	@Override
	public List<ProtonUserRole> getProtonRoleUser() {
		return roleDao.getProtonRoleUser();
	}

	@Override
	public List<ProtonUserRole> getProtonRoleUser(String userName) throws UserNotFound {
		return roleDao.getProtonRoleUser(userName);
	}

	public void createNewRole(String roleName, UserDetailsProton user) {
		roleDao.createNewRole(roleName, user);
	}

	public void createNewRole(String roleName) {
		roleDao.createNewRole(roleName);
	}
	

	public void addRoleToUser(Integer roleId, Integer userId, UserDetailsProton user) {
		roleDao.addRoleToUser(roleId, userId, user);
	}
	
	public void removeRoleFromUser(Integer roleId, Integer userId, UserDetailsProton user) {
		roleDao.removeRoleFromUser(roleId, userId, user);
	}
}
