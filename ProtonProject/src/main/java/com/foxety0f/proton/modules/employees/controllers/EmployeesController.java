package com.foxety0f.proton.modules.employees.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxety0f.proton.common.abstracts.AbstractController;
import com.foxety0f.proton.common.annotations.PageAnnotation;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.common.user.UserDetailsServiceImpl;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.employees.domain.EmployeeTitle;
import com.foxety0f.proton.modules.employees.domain.EmployeesGroup;
import com.foxety0f.proton.modules.employees.domain.EmployeesInformation;
import com.foxety0f.proton.modules.employees.service.IEmployeeService;
import com.foxety0f.proton.modules.help.service.IHelpService;
import com.foxety0f.proton.utils.WebUtils;

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

			System.err.println(user.hasRole("ROLE_ADMIN"));
			udateMenuList(user);
			model.addAttribute("message", "IAMHERE");
			model.addAttribute("employees", employeeService.getEmployeeInformation());
			model.addAttribute("menuList", user.getPages());
			model.addAttribute("helpBox", helpService.getHelp("/employees"));
			return "modules/employees/indexEmployee";
		}

		model.addAttribute("Message", "Access denied");

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
			@RequestParam(required = false, value = "ipAddress") String ipAddress) {
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping("/employees/checkEmployeeLoginExist")
	public ResponseEntity<String> checkEmployeeLoginExist(
			@RequestParam(required = true, value = "login") String login) {
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping("/employees/findUser")
	public ResponseEntity<Map<String, Integer>> findUser(@RequestParam(required = true, value = "login") String login) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping("/employees/getGroups")
	public ResponseEntity<List<EmployeesGroup>> getEmployeesGroups() {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping("/employees/getTitle")
	public ResponseEntity<List<EmployeeTitle>> getTitles() {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping("/employee/updateEmployee")
	public ResponseEntity<String> updateEmployeeInformation(
			@RequestParam(required = true, value = "employeeId") Integer employeeId,
			@RequestParam(required = true, value = "idGroup") Integer idGroup,
			@RequestParam(required = true, value = "titleId") Integer idTitle,
			@RequestParam(required = false, value = "pcNumber") String pcNumber,
			@RequestParam(required = false, value = "placeNumber") String placeNumber,
			@RequestParam(required = false, value = "ipNumber") String ipNumber) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping("/employee/markAsInactive")
	public ResponseEntity<String> markEmployeeAsInactive(
			@RequestParam(required = true, value = "employeeId") Integer employeeId) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping("/employee/markAsActive")
	public ResponseEntity<String> markEmployeeAsActive(
			@RequestParam(required = true, value = "employeeId") Integer employeeId) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}