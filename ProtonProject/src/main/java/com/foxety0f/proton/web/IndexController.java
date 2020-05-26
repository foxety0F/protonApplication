package com.foxety0f.proton.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.admin.service.IAdminService;
import com.foxety0f.proton.utils.WebUtils;

@Controller
public class IndexController {

	@Autowired
	private IAdminService adminService;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model, Principal principal) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "This is welcome page!");

		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			model.addAttribute("menuList", user.getPages());
		}

		return "welcomePage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {

		return "loginPage";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return "403Page";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(Model model, Principal principal) {
		if (principal != null) {
			
			String message = "Hi " 
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return "403Page";
	}

}
