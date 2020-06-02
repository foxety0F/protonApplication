package com.foxety0f.proton.modules.hire.controllers;

import java.security.Principal;
import java.util.List;

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
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredAttributes;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredExperience;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredSkills;
import com.foxety0f.proton.modules.hire.service.IHiredService;

@Controller
public class HiredController extends AbstractController {

	@Autowired
	private IHiredService hiredService;

	@RequestMapping("/hiring")
	@PageAnnotation(value = "Hiring", module = ProtonModules.HIRING)
	@Secured("ROLE_SUPERVISOR_HIRE")
	public String index(Model model, Principal principal) {

		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			model.addAttribute("menuList", user.getPages());
			udateMenuList(user);
			if (user.hasRole("ROLE_SUPERVISOR_HIRE") | user.hasRole("ROLE_ADMIN")) {
				model.addAttribute("employeeList", hiredService.getEmployeeHiredConfig());
				model.addAttribute("skills", hiredService.getHiredSkills());

				return "modules/hired/hiredSuperuserIndex";
			} else if (user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_ADMIN")) {

			}

			List<EmployeeHiredConfig> employeeHired = hiredService
					.getEmployeeHiredConfig(Integer.parseInt(user.getUserId().toString()));
			if (employeeHired.size() > 0) {
				EmployeeHiredConfig emp = employeeHired.get(0);
				String about = emp.getAbout();
				String userPhone = emp.getUserPhone();
				List<EmployeeHiredExperience> experience = hiredService.getEmployeeExperience(emp.getRowId());
				List<EmployeeHiredSkills> skills = hiredService.getEmployeeSkills(emp.getRowId());
				model.addAttribute("about", about);
				model.addAttribute("userPhone", userPhone);
				model.addAttribute("experience", experience);
				model.addAttribute("skills", skills);

				return "modules/hired/hiredUserIndex";
			}

			return "modules/hired/hiredUserIndex";

		} else {
			return "403Page";
		}
	}

	@RequestMapping("/hired/getUserInfo")
	public ResponseEntity<EmployeeHiredAttributes> getUserInfo(Principal principal,
			@RequestParam(required = true, value = "briefId") Integer briefId) {

		ResponseEntity<EmployeeHiredAttributes> result = null;
		
		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (user.hasRole("ROLE_SUPERVISOR_HIRE") | user.hasRole("ROLE_ADMIN")) {
				EmployeeHiredAttributes attr = new EmployeeHiredAttributes();
				attr.setAbout(hiredService.getAbout(briefId));
				attr.setUserPhone(hiredService.getPhone(briefId));
				attr.setExperience(hiredService.getEmployeeExperience(briefId));
				attr.setSkills(hiredService.getEmployeeSkills(briefId));
				attr.setIsCurrentUser(Integer.parseInt(user.getUserId().toString()) == hiredService.getUserId(briefId) ? true : false);
				result = new ResponseEntity<EmployeeHiredAttributes>(attr, HttpStatus.OK);
				return result;
			}else {
				if(Integer.parseInt(user.getUserId().toString()) == hiredService.getUserId(briefId)) {
					EmployeeHiredAttributes attr = new EmployeeHiredAttributes();
					attr.setAbout(hiredService.getAbout(briefId));
					attr.setUserPhone(hiredService.getPhone(briefId));
					attr.setExperience(hiredService.getEmployeeExperience(briefId));
					attr.setSkills(hiredService.getEmployeeSkills(briefId));
					attr.setIsCurrentUser(true);
					result = new ResponseEntity<EmployeeHiredAttributes>(attr, HttpStatus.OK);
					return result;
				}else {
					result = new ResponseEntity<EmployeeHiredAttributes>( HttpStatus.FORBIDDEN);
					return result;
				}
			}
		}
		
		result = new ResponseEntity<EmployeeHiredAttributes>( HttpStatus.FORBIDDEN);
		
		return result;

	}

}
