package com.foxety0f.proton.modules.reports.controller;

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
import com.foxety0f.proton.common.exceptions.ModuleNotUndefinedException;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.reports.domain.MetaColumns;
import com.foxety0f.proton.modules.reports.domain.MetaDatabases;
import com.foxety0f.proton.modules.reports.domain.MetaTables;
import com.foxety0f.proton.modules.reports.exceptions.ColumnIdUpdateException;
import com.foxety0f.proton.modules.reports.exceptions.ColumnMissingException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseIdUpdateException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseNotFoundException;
import com.foxety0f.proton.modules.reports.exceptions.SchemaAndTableMissingException;
import com.foxety0f.proton.modules.reports.exceptions.TableIdUpdateException;
import com.foxety0f.proton.modules.reports.service.IReportsService;

@Controller
public class IndexReportsController extends AbstractController {

	@Autowired
	private IReportsService reportService;
	
	@RequestMapping("/reports")
	@PageAnnotation(value = "Reports", module = ProtonModules.REPORTS)
	@Secured({"ROLE_REPORTS_VIEWER", "ROLE_ADMIN", "ROLE_REPORTS_ADMIN"})
	public String index(Model model, Principal principal) {
		try {
			if(!getAdminService().isActiveModule(ProtonModules.REPORTS)) {
				return "403Page.html";
			}
		} catch (ModuleNotUndefinedException e) {
			e.printStackTrace();
		}
		
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			model.addAttribute("menuList", user.getPages());
			udateMenuList(user);
			model.addAttribute("userProtonDetails", user);
			
			return "modules/reports/indexReports.html";
		}
		
		return null;
	}
	
	@RequestMapping("/reports/control/getDatabases")
	public ResponseEntity<List<MetaDatabases>> getDatabases(Principal principal){
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				return new ResponseEntity<List<MetaDatabases>>(reportService.getDatabases(), HttpStatus.OK);
			}
			
			return new ResponseEntity<List<MetaDatabases>>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<MetaDatabases>>(HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping("/reports/control/getTables")
	public ResponseEntity<List<MetaTables>> getTables(@RequestParam(value = "id", required = true) Integer idDatabase
			, Principal principal){
		
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				return new ResponseEntity<List<MetaTables>>(reportService.getTablesDatabase(idDatabase), HttpStatus.OK);
			}
			
			return new ResponseEntity<List<MetaTables>>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<MetaTables>>(HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping("/reports/control/getColumnsByTable")
	public ResponseEntity<List<MetaColumns>> getColumnsByTable(@RequestParam(value = "id", required = true) Integer idTable
			, Principal principal){
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				return new ResponseEntity<List<MetaColumns>>(reportService.getColumns(idTable), HttpStatus.OK);
			}
			
			return new ResponseEntity<List<MetaColumns>>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<MetaColumns>>(HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping("/reports/control/updateColumn")
	public ResponseEntity<String> updateColumn(@RequestParam(value = "tableId", required = true) Integer tableId,
			@RequestParam(value = "columnId", required = true) Integer columnId,
			@RequestParam(value = "param", required = true) String param,
			@RequestParam(value = "columnValue", required = true) Object columnValue,
			Principal principal){
		
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				try {
					reportService.updateColumnInfo(tableId, columnId, param, columnValue, user);
				} catch (SchemaAndTableMissingException e) {
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				} catch (ColumnMissingException e) {
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				} catch (ColumnIdUpdateException e) {
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				} catch (TableIdUpdateException e) {
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				return new ResponseEntity<String>("Column " + param + " is updated!", HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("/reports/control/updateTable")
	public ResponseEntity<String> updateTable(@RequestParam(value = "tableId", required = true) Integer id,
			@RequestParam(value = "databaseId", required = true) Integer idDatabase,
			@RequestParam(value = "field", required = true) String field,
			@RequestParam(value = "value", required = true) Object value,
			Principal principal){
		
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				try {
					reportService.updateTableInfo(id, idDatabase, field, value, user);
				} catch (DatabaseNotFoundException | TableIdUpdateException | DatabaseIdUpdateException e) {
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				return new ResponseEntity<String>("Column " + value + " is updated!", HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
	}
}
