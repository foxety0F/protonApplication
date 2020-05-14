package com.foxety0f.proton.modules.roles.controllers;

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
import com.foxety0f.proton.modules.roles.domain.ProtonRole;
import com.foxety0f.proton.modules.roles.service.IRoleService;

@Controller
public class IndexRolesController extends AbstractController {

	@Autowired
	private IRoleService roleService;

	@RequestMapping("/roles")
	@PageAnnotation(value = "Roles", module = ProtonModules.ROLES)
	@Secured({ "ROLE_MANAGER_ROLES", "ROLE_ADMIN" })
	public String index(Model model, Principal principal) {

		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			udateMenuList(user);
			model.addAttribute("menuList", user.getPages());

			if (user.hasRole("ROLE_MANAGER_ROLES") | user.hasRole("ROLE_ADMIN")) {
				model.addAttribute("roles", roleService.getProtonRoles());
				model.addAttribute("userAndRoles", roleService.getProtonRoleUser());

				return "modules/roles/indexRole";

			}

			return "403Page";

		} else {
			return "403Page";
		}
	}

	@RequestMapping("/roles/createNewRole")
	public ResponseEntity<String> createNewRole(@RequestParam(value = "roleName", required = true) String roleName,
			@RequestParam(value = "roleDescription", required = false) String roleDescription, Principal princpal) {

		if (princpal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			if (user.hasRole("ROLE_MANAGER_ROLES") | user.hasRole("ROLE_ADMIN")) {
				roleService.createNewRole(roleName, user);
				return new ResponseEntity<String>("OK", HttpStatus.OK);
			}

			return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);

		}

		return new ResponseEntity<String>("Forbidden. Not authorize", HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping("/roles/getAllRoles")
	public ResponseEntity<List<ProtonRole>> getAllRoles(Principal principal) {
		if (principal != null) {
			return new ResponseEntity<List<ProtonRole>>(roleService.getProtonRoles(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping("/roles/addRoleToUser")
	public ResponseEntity<String> addRoleToUser(Principal princpal, @RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "roleId", required = true) Integer roleId) {

		if (princpal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			if (user.hasRole("ROLE_MANAGER_ROLES") | user.hasRole("ROLE_ADMIN")) {
				roleService.addRoleToUser(roleId, userId, user);
				return new ResponseEntity<String>("OK", HttpStatus.OK);
			}

			return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);

		}

		return new ResponseEntity<String>("Forbidden. Not authorize", HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("/roles/removeRoleFromUser")
	public ResponseEntity<String> removeRoleFromUser(Principal princpal, @RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "roleId", required = true) Integer roleId) {

		if (princpal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			if (user.hasRole("ROLE_MANAGER_ROLES") | user.hasRole("ROLE_ADMIN")) {
				roleService.removeRoleFromUser(roleId, userId, user);
				return new ResponseEntity<String>("OK", HttpStatus.OK);
			}

			return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);

		}

		return new ResponseEntity<String>("Forbidden. Not authorize", HttpStatus.UNAUTHORIZED);
	}
}
