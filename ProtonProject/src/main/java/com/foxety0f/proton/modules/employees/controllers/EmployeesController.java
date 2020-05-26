package com.foxety0f.proton.modules.employees.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxety0f.proton.common.abstracts.AbstractController;
import com.foxety0f.proton.common.annotations.PageAnnotation;
import com.foxety0f.proton.common.exceptions.ModuleNotUndefinedException;
import com.foxety0f.proton.common.exceptions.UserAlreadyExistException;
import com.foxety0f.proton.common.exceptions.UserNotFound;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.employees.domain.AlphaUserInformation;
import com.foxety0f.proton.modules.employees.domain.EmployeeTitle;
import com.foxety0f.proton.modules.employees.domain.EmployeesGroup;
import com.foxety0f.proton.modules.employees.domain.EmployeesInformation;
import com.foxety0f.proton.modules.employees.service.IEmployeeService;
import com.foxety0f.proton.modules.help.service.IHelpService;

@Controller
public class EmployeesController extends AbstractController {

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IHelpService helpService;

	@RequestMapping("/employees")
	@PageAnnotation(value = "Employees", module = ProtonModules.EMPLOYEES)
	@Secured("ROLE_USER")
	public String employeesInit(Model model, Principal principal) {
		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			udateMenuList(user);
			model.addAttribute("employees", employeeService.getEmployeeInformation());
			model.addAttribute("menuList", user.getPages());
			model.addAttribute("employeeGroups", employeeService.getEmployeeGroup());
			model.addAttribute("employeeTitles", employeeService.getEmployeeTitles());
			model.addAttribute("isSupervisor", (user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_ADMIN")));
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_ADMIN"))) {
				model.addAttribute("alphaUsers", employeeService.getAlphaUsers());
			}
			try {
				if (getAdminService().isActiveModule(ProtonModules.HELP)) {
					model.addAttribute("helpBox", helpService.getHelp("/employees"));
				}
			} catch (ModuleNotUndefinedException e) {
				e.printStackTrace();
			}
			return "modules/employees/indexEmployee";
		}

		return "403Page";

	}

	@RequestMapping("/employees/getEmployeeInformation")
	public ResponseEntity<List<EmployeesInformation>> getEmployeesInformation() {

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping("/employees/setNewEmployee")
	public ResponseEntity<String> setNewEmployee(@RequestParam(required = true, value = "idUser") Integer idUser,
			@RequestParam(required = true, value = "login") String login,
			@RequestParam(required = true, value = "idGroup") Integer idGroup,
			@RequestParam(required = true, value = "titleId") Integer titleId,
			@RequestParam(required = false, value = "pcNumber") String pcNumber,
			@RequestParam(required = false, value = "placeNumber") String placeNumber,
			@RequestParam(required = false, value = "ipAddress") String ipAddress, Principal principal) {

		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_EMPLOYEE_SUPERVISOR")) {
				try {
					employeeService.setNewEmployee(idUser, login, idGroup, titleId, pcNumber, placeNumber, ipAddress,
							user);
					return new ResponseEntity<String>("User created " + login, HttpStatus.OK);
				} catch (UserAlreadyExistException e) {
					return new ResponseEntity<String>("User already exist in database", HttpStatus.CONFLICT);
				}
			}
		}

		return new ResponseEntity<>("Unauthorize", HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping("/employees/checkEmployeeLoginExist")
	public ResponseEntity<String> checkEmployeeLoginExist(@RequestParam(required = true, value = "login") String login,
			Principal principal) {

		if (principal != null) {

		}

		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping("/employees/findUser")
	public ResponseEntity<Map<String, Integer>> findUser(@RequestParam(required = true, value = "login") String login) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping("/employees/getGroups")
	public ResponseEntity<List<EmployeesGroup>> getEmployeesGroups(Principal principal) {

		if (principal != null) {
			return new ResponseEntity<List<EmployeesGroup>>(employeeService.getEmployeeGroup(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@RequestMapping("/employees/getTitle")
	public ResponseEntity<List<EmployeeTitle>> getTitles(Principal principal) {
		if (principal != null) {
			return new ResponseEntity<List<EmployeeTitle>>(employeeService.getEmployeeTitles(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@RequestMapping("/employee/updateEmployee")
	public ResponseEntity<String> updateEmployeeInformation(
			@RequestParam(required = true, value = "employeeId") Integer employeeId,
			@RequestParam(required = true, value = "idGroup") Integer idGroup,
			@RequestParam(required = true, value = "titleId") Integer idTitle,
			@RequestParam(required = false, value = "pcNumber") String pcNumber,
			@RequestParam(required = false, value = "placeNumber") String placeNumber,
			@RequestParam(required = false, value = "ipNumber") String ipNumber,
			@RequestParam(required = false, value = "titleId") Integer titleId,
			@RequestParam(required = true, value = "startDate") Date startDate,
			@RequestParam(required = true, value = "startDate") Date endDate, Principal principal) {

		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_EMPLOYEE_SUPERVISOR")) {
				try {
					employeeService.updateInformation(employeeId, null, idGroup, titleId, pcNumber, placeNumber,
							ipNumber, startDate, endDate, user);
					return new ResponseEntity<String>("User updated", HttpStatus.OK);
				} catch (UserNotFound e) {
					return new ResponseEntity<String>("User not found in database", HttpStatus.NOT_FOUND);
				}
			}
		}

		return new ResponseEntity<>("Unauthorize", HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping("/employee/markAsInactive")
	public ResponseEntity<String> markEmployeeAsInactive(
			@RequestParam(required = true, value = "employeeId") Integer employeeId, Principal principal) {

		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_EMPLOYEE_SUPERVISOR")) {
				employeeService.markEmployeeAsInactive(employeeId, user);
				return new ResponseEntity<String>("User mark as inactive", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping("/employee/markAsActive")
	public ResponseEntity<String> markEmployeeAsActive(
			@RequestParam(required = true, value = "employeeId") Integer employeeId, Principal principal) {
		
		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_EMPLOYEE_SUPERVISOR")) {
				employeeService.markEmployeeAsActive(employeeId, user);
				return new ResponseEntity<String>("User mark as active", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping("/employees/getAlphaUsers")
	public ResponseEntity<String> getAlphaUsers(Principal principal) {
		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if (user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_ADMIN")) {
				
				return new ResponseEntity<String>(toJson(employeeService.getAlphaUsers()), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);
			}
		}
		
		return new ResponseEntity<String>("UNAUTH", HttpStatus.UNAUTHORIZED);
	}
}