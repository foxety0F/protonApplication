package com.foxety0f.proton.modules.admin.controllers;

import java.io.IOException;
import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.foxety0f.proton.common.abstracts.AbstractController;
import com.foxety0f.proton.common.annotations.PageAnnotation;
import com.foxety0f.proton.common.exceptions.ModuleNotUndefinedException;
import com.foxety0f.proton.common.exceptions.UserAlreadyExistException;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.admin.service.IAdminService;
import com.foxety0f.proton.modules.help.service.IHelpService;
import com.foxety0f.proton.modules.modules_config.Module;

@Controller
public class IndexAdminController extends AbstractController {

	@Autowired
	private IAdminService adminService;

	@Autowired
	private IHelpService helpService;

	@RequestMapping("/admin")
	@PageAnnotation(value = "Admin", module = ProtonModules.ADMIN)
	@Secured("ROLE_ADMIN")
	public String index(Model model, Principal principal) {

		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			model.addAttribute("menuList", user.getPages());
			System.err.println(user.getPages());
			udateMenuList(user);
			if (!user.hasRole("ROLE_ADMIN")) {
				return "403Page";
			}

			model.addAttribute("moduleInformation", adminService.getModuleInformation());
			model.addAttribute("modules", adminService.getAllModules());
			model.addAttribute("logingUsers", adminService.getAuthUsers());
			model.addAttribute("tempTestData", adminService.getTempTestData());
			try {
				if (adminService.isActiveModule(ProtonModules.HELP)) {
					model.addAttribute("helpBox", helpService.getHelp("/admin"));
				}
			} catch (ModuleNotUndefinedException e) {
				e.printStackTrace();
			}

			return "modules/admin/indexAdmin";

		} else {
			return "403Page";
		}
	}

	@RequestMapping("/admin/modules")
	public @ResponseBody List<Module> modules() {
		return adminService.getAllModules();
	}

	@RequestMapping("/admin/changeModuleStatus")
	public ResponseEntity<String> changeStatus(@RequestParam(value = "id", required = false) Integer id,
			Principal principal) {
		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			if (!user.hasRole("ROLE_ADMIN")) {
				return new ResponseEntity<>("Not admin", HttpStatus.FORBIDDEN);
			}

			try {
				String result = adminService.changeActiveStatus(id, user);
				updateMenuListNow(user);
				return new ResponseEntity<>(result, HttpStatus.OK);
			} catch (ModuleNotUndefinedException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		}
		return null;
	}

	@RequestMapping("/admin/createAlphaUser")
	public ResponseEntity<String> createAlphaUser(@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "surname", required = true) String surname,
			@RequestParam(value = "username", required = true) String username, Principal principal) {

		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			try {
				String result = toJson(adminService.createNewUser(username, firstName, surname, user));
				return new ResponseEntity<String>(result, HttpStatus.OK);
			} catch (UserAlreadyExistException e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
			}

		}

		return new ResponseEntity<String>("Not authorize", HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/admin/uploadFiles", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFiles(@RequestParam("files") MultipartFile[] files, Principal principal) {
		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (files != null && user.hasRole("ROLE_ADMIN")) {
				if (files.length > 0) {
					try {
						adminService.uploadFiles(files, ProtonModules.ADMIN, user);
					} catch (IOException e) {
						return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

					}
				}
			}
		}
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/uploadHelp")
	public ResponseEntity<String> uploadHelp(@RequestParam(value = "text", required = true) String htmlText,
			Principal principal) {
		if (principal != null) {
			if (htmlText != null) {
				UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal();
				adminService.uploadFiles(htmlText, ProtonModules.ADMIN, user);
			}
		}
		return new ResponseEntity<String>("Forbidden. Not auth", HttpStatus.UNAUTHORIZED);
	}
}