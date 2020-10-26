package com.foxety0f.proton.modules.hire.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxety0f.proton.common.abstracts.AbstractController;
import com.foxety0f.proton.common.annotations.PageAnnotation;
import com.foxety0f.proton.common.exceptions.ModuleNotUndefinedException;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.help.service.IHelpService;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredAttributes;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredExperience;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredSkills;
import com.foxety0f.proton.modules.hire.service.IHiredService;

@Controller
public class HiredController extends AbstractController {

    @Autowired
    private IHiredService hiredService;

    @Autowired
    private IHelpService helpService;

    @RequestMapping("/hiring")
    @PageAnnotation(value = "Hiring", module = ProtonModules.HIRING)
    @Secured({ "ROLE_SUPERVISOR_HIRE", "ROLE_ADMIN", "ROLE_USER" })
    public String index(Model model, Principal principal) {

	try {
	    if (!getAdminService().isActiveModule(ProtonModules.HIRING)) {
		return "403Page.html";
	    }
	} catch (ModuleNotUndefinedException e) {
	    e.printStackTrace();
	}

	if (principal != null) {
	    UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
		    .getPrincipal();
	    // Get all menu links
	    model.addAttribute("menuList", user.getPages());
	    // Update menu list for current user
	    udateMenuList(user);
	    // Add attribute user to html-page
	    model.addAttribute("userProtonDetails", user);
	    model.addAttribute("contactConfig", hiredService.getContactConfig());

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
		model.addAttribute("user_name", user.getFirstName());
		model.addAttribute("user_surname", user.getSurname());
		model.addAttribute("briefId", emp.getRowId());
		model.addAttribute("contactsSocial", hiredService.getUserContacts(emp.getRowId()));
	    } else {
		hiredService.createNewBrief(user.getUserId(), null, null);
		employeeHired = hiredService.getEmployeeHiredConfig(Integer.parseInt(user.getUserId().toString()));

		model.addAttribute("briefId", employeeHired.get(0).getRowId());
	    }

	    return "modules/hired/hiredUserIndex";

	} else {
	    return "redirect:/login";
	}
    }

    @RequestMapping(value = "/hiring/brief", method = RequestMethod.POST)
    public ResponseEntity<String> postNewBrief(Principal principal,
	    @RequestParam(value = "briefId", required = true) Integer briefId,
	    @RequestParam(value = "companyName", required = false) String companyName,
	    @RequestParam(value = "titleName", required = false) String titleName,
	    @RequestParam(value = "description", required = false) String description,
	    @RequestParam(value = "dateFrom", required = false) Date dateFrom,
	    @RequestParam(value = "dateTo", required = false) Date dateTo,
	    @RequestParam(value = "isCurrent", required = false) Boolean isCurrent) {

	if (principal != null) {
	    UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
		    .getPrincipal();

	}

	return new ResponseEntity<String>("Auth failed", HttpStatus.FORBIDDEN);
    }

}
