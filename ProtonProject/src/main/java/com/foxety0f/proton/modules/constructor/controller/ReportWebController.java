package com.foxety0f.proton.modules.constructor.controller;

import java.security.Principal;
import java.util.ArrayList;
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
import com.foxety0f.proton.modules.constructor.domain.CreateReportColumnEntity;
import com.foxety0f.proton.modules.constructor.domain.initial.CreateConstructorInitial;
import com.foxety0f.proton.modules.constructor.domain.initial.CreateConstructorReportColumn;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorRelationTablesWeb;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTables;
import com.foxety0f.proton.modules.constructor.domain.web.ConstructorTablesRelations;
import com.foxety0f.proton.modules.constructor.service.IReportWebService;
import com.google.gson.Gson;

@Controller
public class ReportWebController extends AbstractController{

    @Autowired
    private IReportWebService web;

    @RequestMapping("/constructor/makeConstructor")
    @PageAnnotation(value = "Create Report", module = ProtonModules.CONSTRUCTOR)
    @Secured({ "ROLE_ADMIN", "ROLE_CONSTRUCTOR_USER" })
    public String index(Model model, Principal principal) {

	try {
	    if (!getAdminService().isActiveModule(ProtonModules.CONSTRUCTOR)) {
		return "403Page.html";
	    }else {
		UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
			.getPrincipal();
		udateMenuList(user);
		model.addAttribute("menuList", user.getPages());
		model.addAttribute("systems", web.getSystems());
		return "modules/constructor/mainPage";
	    }
	} catch (ModuleNotUndefinedException e) {
	    e.printStackTrace();
	}

	return null;
    }
    
    @RequestMapping(value = "/constructor/makeConstructor/getRelations", method = RequestMethod.GET)
    public ResponseEntity<?> getRelations(@RequestParam(value = "threadId", required = false) Integer threadId,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.CONSTRUCTOR)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_CONSTRUCTOR_USER"))){
			    return new ResponseEntity<List<ConstructorTablesRelations>>(web.getRelations(threadId), HttpStatus.OK);
			}else {
			    return new ResponseEntity<String>("Access denied", HttpStatus.FORBIDDEN);
			}
		    }
		} catch (ModuleNotUndefinedException e) {
		    e.printStackTrace();
		}
	}

	return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
    }
    
    @RequestMapping(value = "/constructor/makeConstructor/getTables", method = RequestMethod.GET)
    public ResponseEntity<?> getTables(@RequestParam(value = "threadId", required = false) Integer threadId,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.CONSTRUCTOR)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_CONSTRUCTOR_USER"))){
			    return new ResponseEntity<List<ConstructorTables>>(web.getTablesForWeb(threadId), HttpStatus.OK);
			}else {
			    return new ResponseEntity<String>("Access denied", HttpStatus.FORBIDDEN);
			}
		    }
		} catch (ModuleNotUndefinedException e) {
		    e.printStackTrace();
		}
	}

	return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
    }
    
    @RequestMapping(value = "/constructor/makeConstructor/getRelationsAndTables", method = RequestMethod.GET)
    public ResponseEntity<?> getRelationsTables(@RequestParam(value = "threadId", required = false) Integer threadId,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.CONSTRUCTOR)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_CONSTRUCTOR_USER"))){
			    ConstructorRelationTablesWeb relTabs = new ConstructorRelationTablesWeb();
			    relTabs.setRelations(web.getRelations(threadId));
			    relTabs.setTables(web.getTablesForWeb(threadId));
			    return new ResponseEntity<ConstructorRelationTablesWeb>(relTabs, HttpStatus.OK);
			}else {
			    return new ResponseEntity<String>("Access denied", HttpStatus.FORBIDDEN);
			}
		    }
		} catch (ModuleNotUndefinedException e) {
		    e.printStackTrace();
		}
	}

	return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
    }
    
    @RequestMapping(value = "/constructor/makeConstructor/createReport", method = RequestMethod.GET)
    public ResponseEntity<?> createReport(@RequestParam(value = "columns", required = false) String initial,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.CONSTRUCTOR)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_CONSTRUCTOR_USER"))){
			    Gson gson = new Gson();
			    CreateConstructorInitial init = new Gson().fromJson(initial, CreateConstructorInitial.class);
			    return new ResponseEntity<String>(web.testSelect(init), HttpStatus.OK);
			}else {
			    return new ResponseEntity<String>("Access denied", HttpStatus.FORBIDDEN);
			}
		    }
		} catch (ModuleNotUndefinedException e) {
		    e.printStackTrace();
		}
	}

	return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
    }
    
}
