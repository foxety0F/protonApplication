package com.foxety0f.proton.modules.reporting.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxety0f.proton.common.abstracts.AbstractController;
import com.foxety0f.proton.common.annotations.PageAnnotation;
import com.foxety0f.proton.common.exceptions.ModuleNotUndefinedException;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.reporting.domain.ReportingColumnType;
import com.foxety0f.proton.modules.reporting.domain.ReportingSystemInformation;
import com.foxety0f.proton.modules.reporting.service.IReportingConfigurationService;

@Controller
public class ReportingAdminController extends AbstractController {

    @Autowired
    private IReportingConfigurationService reportingService;

    @RequestMapping("/reporting/admin")
    @PageAnnotation(value = "Reporting Admin", module = ProtonModules.REPORTS)
    @Secured({ "ROLE_ADMIN", "ROLE_REPORTING_ADMIN" })
    public String index(Model model, Principal principal) {

	try {
	    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
		return "403Page.html";
	    }else {
		UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
			.getPrincipal();
		udateMenuList(user);
		model.addAttribute("menuList", user.getPages());
		model.addAttribute("systems", reportingService.getSystemInformation());
		model.addAttribute("metaSystem", reportingService.getMetaSystemTypes());
		model.addAttribute("columnTypes", reportingService.getMetaColumnTypes());
		model.addAttribute("columnTypesMapper", reportingService.getColumnTypesMapping());
		model.addAttribute("tables", reportingService.getTables());
		model.addAttribute("columns", reportingService.getColumns());
		return "modules/reporting/adminPage";
	    }
	} catch (ModuleNotUndefinedException e) {
	    e.printStackTrace();
	}

	return null;
    }

    @RequestMapping(value = "/reporting/admin/system/addSystem", method = RequestMethod.POST)
    public ResponseEntity<?> addSystem(@RequestParam(value = "systemType", required = false) Integer systemType,
	    @RequestParam(value = "title", required = true) String title,
	    @RequestParam(value = "description", required = false) String description,
	    @RequestParam(value = "userName", required = true) String userName,
	    @RequestParam(value = "password", required = true) String password,
	    @RequestParam(value = "connectionConfig", required = true) String connectionConfig, Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    reportingService.addSystem(systemType, title, description, userName, password, connectionConfig, user);
			    return new ResponseEntity<List<ReportingSystemInformation>>(reportingService.getSystemInformation(), HttpStatus.OK);
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

    @RequestMapping(value = "/reporting/admin/system/changeSystem", method = RequestMethod.POST)
    public ResponseEntity<?> changeSystem(@RequestParam(value = "systemType", required = false) Integer systemType,
	    @RequestParam(value = "title", required = true) String title,
	    @RequestParam(value = "description", required = false) String description,
	    @RequestParam(value = "userName", required = true) String userName,
	    @RequestParam(value = "password", required = true) String password,
	    @RequestParam(value = "connectionConfig", required = true) String connectionConfig,
	    @RequestParam(value = "systemId", required = false) Integer id, Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    reportingService.changeSystem(id, systemType, title, description, userName, password, connectionConfig, user);
			    return new ResponseEntity<List<ReportingSystemInformation>>(reportingService.getSystemInformation(), HttpStatus.OK);
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

    @RequestMapping(value = "/reporting/admin/system/markSystemAsInactive" , method = RequestMethod.POST)
    public ResponseEntity<?> markSystemInactive(@RequestParam(value = "systemId", required = true) Integer systemId,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    reportingService.markSystemInactive(systemId, user);
			    return new ResponseEntity<List<ReportingSystemInformation>>(reportingService.getSystemInformation(), HttpStatus.OK);
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

    @RequestMapping(value = "/reporting/admin/system/markSystemAsActive", method = RequestMethod.POST)
    public ResponseEntity<?> markSystemActive(@RequestParam(value = "systemId", required = true) Integer systemId,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    reportingService.markSystemActive(systemId, user);
			    return new ResponseEntity<List<ReportingSystemInformation>>(reportingService.getSystemInformation(), HttpStatus.OK);
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
    
    @RequestMapping("/reporting/admin/column/addColumnType")
    public ResponseEntity<?> addColumnType(@RequestParam(value = "name", required = true) String name,
	    @RequestParam(value = "description", required = false) String description,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    reportingService.setColumnType(name, description, user);
			    return new ResponseEntity<List<ReportingColumnType>>(reportingService.getMetaColumnTypes(), HttpStatus.OK);
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
    
    @RequestMapping("/reporting/admin/column/markColumnTypeAsInactive")
    public ResponseEntity<?> markColumnTypeInactive(@RequestParam(value = "columnTypeId", required = true) Integer columnTypeId,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    
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
    
    @RequestMapping("/reporting/admin/column/markColumnTypeAsActive")
    public ResponseEntity<?> markColumnTypeActive(@RequestParam(value = "columnTypeId", required = true) Integer columnTypeId,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    
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
    
    @RequestMapping("/reporting/admin/column/changeColumnType")
    public ResponseEntity<?> changeColumnType(@RequestParam(value = "name", required = true) String name,
	    @RequestParam(value = "description", required = false) String description,
	    @RequestParam(value = "columnTypeId", required = true) Integer id,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    
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
    
    @RequestMapping("/reporting/admin/thread/addThread")
    public ResponseEntity<?> createThread(
	    @RequestParam(value = "title", required = true) String title,
	    @RequestParam(value = "description", required = false) String description,
	    @RequestParam(value = "coreId", required = false) Integer coreId,
	    @RequestParam(value = "isNativeLang", required = false) Boolean isNativeLang,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    
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
    
    @RequestMapping("/reporting/admin/thread/markThreadAsInactive")
    public ResponseEntity<?> markThreadInactive(@RequestParam(value = "threadId", required = true) Integer threadId,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    
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
    
    @RequestMapping("/reporting/admin/thread/markThreadAsActive")
    public ResponseEntity<?> markThreadActive(@RequestParam(value = "threadId", required = true) Integer threadId,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    
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
    
    @RequestMapping("/reporting/admin/thread/changeThread")
    public ResponseEntity<?> changeThread(
	    @RequestParam(value = "threadId", required = true) Integer threadId,
	    @RequestParam(value = "title", required = true) String title,
	    @RequestParam(value = "description", required = false) String description,
	    @RequestParam(value = "coreId", required = false) Integer coreId,
	    @RequestParam(value = "isNativeLang", required = false) Boolean isNativeLang,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    
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
    
    @RequestMapping("/reporting/admin/column/addColumnTypesMapper")
    public ResponseEntity<?> createColumnTypesMapper(
	    @RequestParam(value = "typeId", required = true) Integer typeId,
	    @RequestParam(value = "systemId", required = true) Integer systemId,
	    @RequestParam(value = "name", required = false) String name,
	    @RequestParam(value = "value", required = false) String value,
	    @RequestParam(value = "pattern", required = false) String pattern,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    
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
    
    @RequestMapping("/reporting/admin/column/markColumnTypesMapperAsInactive")
    public ResponseEntity<?> markColumnTypesMapperInactive(@RequestParam(value = "columnTypeMapperId", required = true) Integer columnTypeMapperId,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    
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
    
    @RequestMapping("/reporting/admin/column/markColumnTypesMapperAsActive")
    public ResponseEntity<?> markColumnTypesMapperActive(@RequestParam(value = "columnTypeMapperId", required = true) Integer columnTypeMapperId,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    
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
    
    @RequestMapping("/reporting/admin/thread/changeColumnTypesMapper")
    public ResponseEntity<?> changeColumnTypesMapper(
	    @RequestParam(value = "columnTypeMapperId", required = true) Integer columnTypeMapperId,
	    @RequestParam(value = "typeId", required = true) Integer typeId,
	    @RequestParam(value = "systemId", required = true) Integer systemId,
	    @RequestParam(value = "name", required = false) String name,
	    @RequestParam(value = "value", required = false) String value,
	    @RequestParam(value = "pattern", required = false) String pattern,
	    Principal principal) {

	if (principal != null) {
	    try {
		    if (!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
			return new ResponseEntity<String>("Module not available", HttpStatus.INTERNAL_SERVER_ERROR);
		    }else {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			if((user.hasRole("ROLE_EMPLOYEE_SUPERVISOR") | user.hasRole("ROLE_REPORTING_ADMIN"))){
			    
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
