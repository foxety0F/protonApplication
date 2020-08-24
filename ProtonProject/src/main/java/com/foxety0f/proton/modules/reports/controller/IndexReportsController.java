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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxety0f.proton.common.abstracts.AbstractController;
import com.foxety0f.proton.common.annotations.PageAnnotation;
import com.foxety0f.proton.common.exceptions.ModuleNotUndefinedException;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.reports.domain.MetaColumns;
import com.foxety0f.proton.modules.reports.domain.MetaDatabases;
import com.foxety0f.proton.modules.reports.domain.MetaTables;
import com.foxety0f.proton.modules.reports.domain.MetaTablesRelations;
import com.foxety0f.proton.modules.reports.domain.MetaThreads;
import com.foxety0f.proton.modules.reports.exceptions.ColumnIdUpdateException;
import com.foxety0f.proton.modules.reports.exceptions.ColumnMissingException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseConnectionAlreadyExistException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseIdUpdateException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseNotFoundException;
import com.foxety0f.proton.modules.reports.exceptions.DatabasePasswordMissingException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseTypeExistException;
import com.foxety0f.proton.modules.reports.exceptions.DatabaseUserMissingException;
import com.foxety0f.proton.modules.reports.exceptions.RelationMissingException;
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
	
	@RequestMapping(value = "/reports/control/updateColumn" , method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/reports/control/updateTable", method = RequestMethod.POST)
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
				
				return new ResponseEntity<String>("Column " + field + " is updated!", HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "/reports/control/createNewDatabase", method = RequestMethod.POST)
	public ResponseEntity<String> createNewDatabase(@RequestParam(value = "dataType", required = true) Integer dataType,
			@RequestParam(value = "url", required = true) String url,
			@RequestParam(value = "username", required = true) String userName,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "driverName", required = true) String driverName,
			@RequestParam(value = "testSql", required = true) String testSql,
			@RequestParam(value = "databaseName", required = true) String databaseName,
			Principal principal){
		
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				try {
					reportService.createNewDatabaseConnection(dataType, url, userName, password, driverName, testSql, databaseName, user);
				} catch (DatabaseConnectionAlreadyExistException | DatabasePasswordMissingException
						| DatabaseUserMissingException e) {
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				return new ResponseEntity<String>("Database " + databaseName + " is created!", HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "/reports/control/createNewDatabaseType", method = RequestMethod.POST)
	public ResponseEntity<String> createNewDatabaseType(
			@RequestParam(value = "name", required = true) String name,

			Principal principal){
		
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				try {
					reportService.createNewDatabaseType(name, user);
				} catch (DatabaseTypeExistException e) {
					e.printStackTrace();
				}
				
				return new ResponseEntity<String>("Database type " + name + " is created!", HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("/reports/control/threadsFromDatabase")
	public ResponseEntity<List<MetaThreads>> getThreadsFromDatabase(
			@RequestParam(value = "name", required = true) Integer database,
			Principal principal){
		
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				
				return new ResponseEntity<List<MetaThreads>>(reportService.getThreads(database), HttpStatus.OK);
			}
		}
		
		return null;
	}
	
	@RequestMapping("/reports/control/getDatabaseInfo")
	public ResponseEntity<?> getDatabaseInfo(@RequestParam(value = "idDatabase", required = true) Integer idDatabase,
			Principal principal){
		
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				
				return new ResponseEntity<MetaDatabases>(reportService.getDatabases(idDatabase).get(0), HttpStatus.OK);
			}
			
			return new ResponseEntity<String>("FORBIDDEN", HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
		
	}
	
	@RequestMapping("/reports/control/getRelations")
	public ResponseEntity<?> getRelations(@RequestParam(value = "tableId", required = true) Integer tableId,
			Principal principal){
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				
				return new ResponseEntity<List<MetaTablesRelations>>(reportService.getTablesRelations(tableId), HttpStatus.OK);
			}
			
			return new ResponseEntity<String>("FORBIDDEN", HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "/reports/control/createRelation", method = RequestMethod.POST)
	public ResponseEntity<?> createRelation(Principal principal,
			@RequestParam(value = "idColumn", required = true) Integer idColumn,
			@RequestParam(value = "idColumnSup", required = true) Integer idColumnSup,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "isActive", required = false) Boolean isActive){
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				try {
					reportService.setNewRelation(idColumn, idColumnSup, name, description, isActive, user);
				} catch (Exception e) {
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
				return new ResponseEntity<String>("OK", HttpStatus.OK);
			}
			
			return new ResponseEntity<String>("FORBIDDEN", HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "/reports/control/updateRelation", method = RequestMethod.POST)
	public ResponseEntity<?> updateRelation(Principal principal,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "columnName", required = true) String columnName,
			@RequestParam(value = "value", required = true) Object value){
		if(principal != null) {
			UserDetailsProton user = (UserDetailsProton) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			if(user.hasRole("ROLE_ADMIN") | user.hasRole("ROLE_REPORTS_ADMIN")) {
				try {
					reportService.updateRelation(id, columnName, value, user);
					return ResponseEntity.ok("OK");
				} catch (RelationMissingException e) {
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				} catch (Exception e) {
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
			return new ResponseEntity<String>("FORBIDDEN", HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
	}
	
	
	
}
