package com.foxety0f.proton.modules.admin.service;

import java.io.IOException;
import java.util.ArrayList;
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
import com.foxety0f.proton.modules.admin.dao.IAdminDAO;
import com.foxety0f.proton.modules.admin.domain.AdminModuleInformation;
import com.foxety0f.proton.modules.modules_config.Module;

public class AdminService implements IAdminService {

	private ProtonModules thisModule = ProtonModules.ADMIN;
	private List<Module> allModules = new ArrayList<Module>();

	private List<AuthenticatedUserLog> authUsers = new ArrayList<AuthenticatedUserLog>();

	public List<AuthenticatedUserLog> getAuthUsers() {
		return authUsers;
	}

	public void setAuthUsers(List<AuthenticatedUserLog> authUsers) {
		this.authUsers = authUsers;
	}
	
	public void addAuth(AuthenticatedUserLog authLog) {
		this.authUsers.add(authLog);
	}

	private IAdminDAO adminDao;

	public AdminService(IAdminDAO adminDao) {
		this.adminDao = adminDao;
		loadAllModules();
	}

	public ProtonModules getThisModule() {
		return thisModule;
	}

	public void loadAllModules() {
		this.allModules = adminDao.getAllModules();
	}

	public List<Module> getAllModules() {
		return this.allModules;
	}

	@Override
	public Module getModule(ProtonModules module) throws ModuleNotUndefinedException {
		for (Module md : this.allModules) {
			if (md.getModule() == module) {
				return md;
			}
		}
		throw new ModuleNotUndefinedException(module);
	}

	@Override
	public Module getModule(int id) throws ModuleNotUndefinedException {
		for (Module md : this.allModules) {
			if (id == md.getModule().moduleValue()) {
				return md;
			}
		}
		throw new ModuleNotUndefinedException(id);
	}

	@Override
	public Module getModule(String name) throws ModuleNotUndefinedException {
		for (Module md : this.allModules) {
			if (name == md.getModule().moduleName()) {
				return md;
			}
		}
		throw new ModuleNotUndefinedException(name);
	}

	@Override
	public String changeActiveStatus(ProtonModules module, UserDetailsProton user) throws ModuleNotUndefinedException {
		for (Module md : this.allModules) {
			if (md.getModule() == module) {
				String mdGen = "";
				if (md.getIsActive() == false) {
					md.setIsActive(true);
					mdGen = adminDao.generateDBModuleStruct(md);
				} else {
					md.setIsActive(false);
				}
				adminDao.changeModueStatus(md.getModule().moduleValue(), user);

				return "Status has been change. Current status is - " + md.getIsActive().toString() + "<br>" + mdGen;
			}
		}
		throw new ModuleNotUndefinedException(module);
	}

	@Override
	public String changeActiveStatus(Integer id, UserDetailsProton user) throws ModuleNotUndefinedException {
		for (Module md : this.allModules) {
			if (id == md.getModule().moduleValue()) {
				String mdGen = "";
				if (md.getIsActive() == false) {
					md.setIsActive(true);
					mdGen = adminDao.generateDBModuleStruct(md);
				} else {
					md.setIsActive(false);
				}
				adminDao.changeModueStatus(md.getModule().moduleValue(), user);

				return "Status has been change. Current status is - " + md.getIsActive().toString() + "<br>" + mdGen;
			}
		}
		throw new ModuleNotUndefinedException(id);
	}

	@Override
	public String changeActiveStatus(String name, UserDetailsProton user) throws ModuleNotUndefinedException {
		for (Module md : this.allModules) {
			if (name == md.getModule().moduleName()) {
				String mdGen = "";
				if (md.getIsActive() == false) {
					md.setIsActive(true);
					mdGen = adminDao.generateDBModuleStruct(md);
				} else {
					md.setIsActive(false);
				}
				adminDao.changeModueStatus(md.getModule().moduleValue(), user);

				return "Status has been change. Current status is - " + md.getIsActive().toString() + "<br>" + mdGen;
			}
		}
		throw new ModuleNotUndefinedException(name);
	}

	@Override
	public Boolean isActiveModule(ProtonModules module) throws ModuleNotUndefinedException {
		for (Module md : this.allModules) {
			if (md.getModule() == module) {
				return md.getIsActive();
			}
		}
		throw new ModuleNotUndefinedException(module);
	}

	@Override
	public Boolean isActiveModule(int id) throws ModuleNotUndefinedException {
		for (Module md : this.allModules) {
			if (id == md.getModule().moduleValue()) {
				return md.getIsActive();
			}
		}
		throw new ModuleNotUndefinedException(id);
	}

	@Override
	public Boolean isActiveModule(String name) throws ModuleNotUndefinedException {
		for (Module md : this.allModules) {
			if (name == md.getModule().moduleName()) {
				return md.getIsActive();
			}
		}
		throw new ModuleNotUndefinedException(name);
	}

	public List<Module> getAllActiveModule() {
		List<Module> result = new ArrayList<Module>();
		for (Module module : this.allModules) {
			if (module.getIsActive() == true) {
				result.add(module);
			}
		}
		return result;
	}

	public List<Module> getAllDisableModule() {
		List<Module> result = new ArrayList<Module>();
		for (Module module : this.allModules) {
			if (module.getIsActive() == false) {
				result.add(module);
			}
		}
		return result;
	}

	public Integer testSQL() {
		return adminDao.testSQL();
	}

	public AppUser createNewUser(String username, String firstName, String surname, UserDetailsProton userDetails)
			throws UserAlreadyExistException {
		return adminDao.createNewUser(username, firstName, surname, userDetails);
	}

	public List<AdminModuleInformation> getModuleInformation() {
		return adminDao.getModulesInformation();
	}

	public void uploadFiles(MultipartFile[] files, ProtonModules module, UserDetailsProton user) throws IOException {
		for(MultipartFile file : files) {
			System.err.println(file);
				
				byte[] byteArray = file.getBytes();
				adminDao.uploadFiles(byteArray, file.getOriginalFilename(), user.getUserId(), module);
		}
	}
	
	/*public void uploadFiles(String val, ProtonModules module, UserDetailsProton user) {
		
		String fileName = RandomStringUtils.random(10, 0, 40, true, true,
				"qwertyuhjklzxcvmQWERTYFGHJKLZXCVBNM124567890".toCharArray());
		
		adminDao.uploadFiles(val, fileName, user.getUserId(), module);
	}*/
	
	public List<LoadedFiles> getFiles(){
		return adminDao.getFiles();
	}

	@Override
	public void uploadFiles(String val, ProtonModules module, UserDetailsProton user) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Map<String, Object>> getTempTestData(){
		return adminDao.getTempTestData();
	}
}
