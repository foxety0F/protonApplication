package com.foxety0f.proton.modules.help.controllers;

import java.security.Principal;

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
import com.foxety0f.proton.modules.help.domain.HelpInformation;
import com.foxety0f.proton.modules.help.service.IHelpService;

/**
 * Controller to access edit help bootboxes
 * extends core AbstractController
 */
@Controller
public class HelpController extends AbstractController {

	@Autowired
	private IHelpService helpService;

	/**
	 * Using for draw core help-page
	 * @return template from "modules/help/indexHelp" path with model
	 */
	@RequestMapping("/help")
	@PageAnnotation(value = "Help Page", module = ProtonModules.HELP)
	@Secured({ "ROLE_ADMIN", "ROLE_HELP_EDITOR" })
	public String index(Model model, Principal principal) {
		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			model.addAttribute("menuList", user.getPages());
			model.addAttribute("helpBox", helpService.getHelp("/help"));

			if (user.hasRole("ROLE_HELP_EDITOR") | user.hasRole("ROLE_ADMIN")) {
				model.addAttribute("allPages", getAllPages());
				model.addAttribute("allHelpPages", helpService.getAllHelps());

			}
		}

		return "modules/help/indexHelp";
	}

	/**
	 * REST-url for getting help from selected url (from table with all helps)
	 * @param String requestUrl -  string-value url
	 * @param Principal principal - using for check auth
	 */
	@RequestMapping("/help/getHelp")
	public HelpInformation getHelp(@RequestParam(value = "requestUr", required = true) String url,
			Principal principal) {
		if (principal != null)
			return helpService.getHelp(url);

		return null;
	}

	/**
	 * REST-url for create new help
	 * @param String helpName - using for specify help name and viewed on bootbox title
	 * @param String helpDescription - using for specify help description and it is informative in nature
	 * @param String helpText - specify help content and save asis with html-tags
	 * @param String helpUrl - specify target url for shows help
	 */
	@RequestMapping("/help/createNewHelp")
	public ResponseEntity<String> createNewHelp(Principal principal,
			@RequestParam(value = "helpName", required = true) String helpName,
			@RequestParam(value = "helpDescription", required = false) String helpDescription,
			@RequestParam(value = "helpText", required = true) String helpText,
			@RequestParam(value = "helpUrl", required = true) String helpUrl) {
		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			if (user.hasRole("ROLE_HELP_EDITOR") | user.hasRole("ROLE_ADMIN")) {
				helpService.createNewHelp(helpName, helpDescription, helpUrl, 100, helpText, user);

				return new ResponseEntity<String>("OK", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);
			}
		}

		return new ResponseEntity<String>("No", HttpStatus.UNAUTHORIZED);
	}

	/**
	 * REST-url for change help
	 * @param String helpName - using for specify help name and viewed on bootbox title
	 * @param String helpDescription - using for specify help description and it is informative in nature
	 * @param String helpText - specify help content and save asis with html-tags
	 * @param String helpUrl - specify target url for shows help
	 * @param Integer helpId - unique help id from database, using for specify modified help
	 */
	@RequestMapping("/help/editHelp")
	public ResponseEntity<String> editHelp(Principal principal,
			@RequestParam(value = "helpName", required = true) String helpName,
			@RequestParam(value = "helpDescription", required = false) String helpDescription,
			@RequestParam(value = "helpText", required = true) String helpText,
			@RequestParam(value = "helpUrl", required = false) String helpUrl,
			@RequestParam(value = "helpId", required = true) Integer helpId) {
		if (principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			if (user.hasRole("ROLE_HELP_EDITOR") | user.hasRole("ROLE_ADMIN")) {

				if (helpName == null) {
					helpService.updateHelp(helpId, helpText, user);
				} else {
					helpService.updateHelp(helpId, helpName, helpDescription, null, 100, helpText, user);
				}

				return new ResponseEntity<String>("OK", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);
			}
		}

		return new ResponseEntity<String>("No", HttpStatus.UNAUTHORIZED);
	}

}
