package com.foxety0f.proton.modules.admin.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.foxety0f.proton.common.domain.LoadedFiles;
import com.foxety0f.proton.common.exceptions.ModuleNotUndefinedException;
import com.foxety0f.proton.common.exceptions.UserAlreadyExistException;
import com.foxety0f.proton.common.user.AppUser;
import com.foxety0f.proton.common.user.AuthenticatedUserLog;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.admin.domain.AdminModuleInformation;
import com.foxety0f.proton.modules.modules_config.Module;

public interface IAdminService {

	Module getModule(ProtonModules module) throws ModuleNotUndefinedException;

	Module getModule(int id) throws ModuleNotUndefinedException;

	Module getModule(String name) throws ModuleNotUndefinedException;

	String changeActiveStatus(ProtonModules module, UserDetailsProton user) throws ModuleNotUndefinedException;

	String changeActiveStatus(Integer id, UserDetailsProton user) throws ModuleNotUndefinedException;

	String changeActiveStatus(String name, UserDetailsProton user) throws ModuleNotUndefinedException;

	Boolean isActiveModule(String name) throws ModuleNotUndefinedException;

	Boolean isActiveModule(int id) throws ModuleNotUndefinedException;

	Boolean isActiveModule(ProtonModules module) throws ModuleNotUndefinedException;

	Integer testSQL();
	
	List<Module> getAllModules();
	
	AppUser createNewUser(String username, String firstName, String surname, UserDetailsProton userDetails) throws UserAlreadyExistException;
	
	List<AdminModuleInformation> getModuleInformation();
	
	List<AuthenticatedUserLog> getAuthUsers();

	void setAuthUsers(List<AuthenticatedUserLog> authUsers);
	
	void addAuth(AuthenticatedUserLog authLog);
	
	void uploadFiles(MultipartFile[] files, ProtonModules module, UserDetailsProton user) throws IOException;
	
	public void uploadFiles(String val, ProtonModules module, UserDetailsProton user);
	
	List<LoadedFiles> getFiles();
	
	List<Map<String, Object>> getTempTestData();
}
