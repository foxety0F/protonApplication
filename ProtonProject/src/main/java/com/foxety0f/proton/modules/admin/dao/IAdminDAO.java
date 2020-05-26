package com.foxety0f.proton.modules.admin.dao;

import java.util.List;
import java.util.Map;

import com.foxety0f.proton.common.domain.LoadedFiles;
import com.foxety0f.proton.common.exceptions.UserAlreadyExistException;
import com.foxety0f.proton.common.user.AppUser;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.admin.domain.AdminModuleInformation;
import com.foxety0f.proton.modules.modules_config.Module;

public interface IAdminDAO {

	List<Module> getAllModules();

	void changeModueStatus(Integer id, UserDetailsProton user);

	Integer testSQL();

	AppUser createNewUser(String username, String firstName, String surname, UserDetailsProton userDetails)
			throws UserAlreadyExistException;

	List<AdminModuleInformation> getModulesInformation();

	String generateDBModuleStruct(Module module);

	void uploadFiles(byte[] base64Encoded, String fileName, Long userId, ProtonModules module);

	List<LoadedFiles> getFiles();
	
	List<Map<String, Object>> getTempTestData();
}
