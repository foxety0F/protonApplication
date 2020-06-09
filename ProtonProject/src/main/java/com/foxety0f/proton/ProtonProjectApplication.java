package com.foxety0f.proton;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.foxety0f.proton.common.user.UserDetailsServiceImpl;
import com.foxety0f.proton.database.DatabaseBeanFactory;
import com.foxety0f.proton.database.DatabaseNames;
import com.foxety0f.proton.modules.admin.dao.AdminDAO;
import com.foxety0f.proton.modules.admin.dao.IAdminDAO;
import com.foxety0f.proton.modules.admin.service.AdminService;
import com.foxety0f.proton.modules.admin.service.IAdminService;
import com.foxety0f.proton.modules.employees.dao.EmployeesDAO;
import com.foxety0f.proton.modules.employees.dao.IEmployeesDAO;
import com.foxety0f.proton.modules.employees.service.EmployeeService;
import com.foxety0f.proton.modules.employees.service.IEmployeeService;
import com.foxety0f.proton.modules.help.dao.HelpDao;
import com.foxety0f.proton.modules.help.dao.IHelpDao;
import com.foxety0f.proton.modules.help.service.HelpService;
import com.foxety0f.proton.modules.help.service.IHelpService;
import com.foxety0f.proton.modules.hire.dao.HiredDAO;
import com.foxety0f.proton.modules.hire.dao.IHiredDAO;
import com.foxety0f.proton.modules.hire.service.HiredService;
import com.foxety0f.proton.modules.hire.service.IHiredService;
import com.foxety0f.proton.modules.roles.dao.IRoleDAO;
import com.foxety0f.proton.modules.roles.dao.RoleDAO;
import com.foxety0f.proton.modules.roles.service.IRoleService;
import com.foxety0f.proton.modules.roles.service.RoleService;

@SpringBootApplication
@ComponentScan
public class ProtonProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtonProjectApplication.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySources() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataSource createDataSource() {
		return new DatabaseBeanFactory().createDataSource(DatabaseNames.CORE.name());
	}

	@Bean
	public IAdminDAO adminDao() {
		return new AdminDAO(createDataSource());
	}

	@Bean
	public IAdminService adminService() {
		return new AdminService(adminDao());
	}

	@Bean
	public UserDetailsServiceImpl userDetailsServiceImpl() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public IEmployeesDAO employeeDao() {
		return new EmployeesDAO(createDataSource());
	}

	@Bean
	public IEmployeeService employeeService() {
		return new EmployeeService(employeeDao());
	}

	@Bean
	public IRoleDAO roleDao() {
		return new RoleDAO(createDataSource());
	}

	@Bean
	public IRoleService roleService() {
		return new RoleService(roleDao());
	}

	@Bean(name = "commonsMultipartResolver")
	public MultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}


	@Bean
	public MultipartConfigElement multipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();

	    factory.setMaxFileSize(DataSize.ofMegabytes(10));
	    factory.setMaxRequestSize(DataSize.ofMegabytes(10));

	    return factory.createMultipartConfig();
	}

	@Bean
	public IHelpDao helpDao() {
		return new HelpDao(createDataSource());
	}
	
	@Bean
	public IHelpService helpService() {
		return new HelpService(helpDao());
	}
	
	@Bean
	public IHiredDAO hiredDao() {
		return new HiredDAO(createDataSource());
	}
	
	@Bean
	public IHiredService hiredService() {
		return new HiredService(hiredDao());
	}
}