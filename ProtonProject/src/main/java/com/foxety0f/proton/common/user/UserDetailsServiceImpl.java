package com.foxety0f.proton.common.user;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxety0f.proton.ProtonProjectApplication;
import com.foxety0f.proton.common.annotations.PageAnnotation;
import com.foxety0f.proton.common.domain.ProtonPageUrl;
import com.foxety0f.proton.common.exceptions.ModuleNotUndefinedException;
import com.foxety0f.proton.common.repository.AppRoleDAO;
import com.foxety0f.proton.common.repository.AppUserDAO;
import com.foxety0f.proton.modules.admin.service.IAdminService;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserDAO appUserDAO;

	@Autowired
	private AppRoleDAO appRoleDAO;

	@Autowired
	private IAdminService adminService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserDAO.findUserAccount(username);

		if (appUser == null) {
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User" + username + " was not found in the database");
		}

		// System.out.println("Found User: " + appUser);

		List<String> roleNames = appRoleDAO.getRoleNames(appUser.getUserId());

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		try {
			if (!adminService.getAuthUsers().isEmpty()) {
				for (AuthenticatedUserLog au : adminService.getAuthUsers()) {
					if (au.getUserName().equals(appUser.getUserName())) {
						adminService.getAuthUsers().remove(au);
					}
				}
			}
		}catch(Exception e) {
			
		}

		

		UserDetails userDetails = new UserDetailsProton(appUser.getUserName(), appUser.getEncryptedPassword(),
				grantList, appUser.getFirstName(), appUser.getSurname(), appUser.getIsFirstTime(),
				pageForUser(grantList), 10, appUser.getUserId());

		return userDetails;
	}

	@SuppressWarnings("rawtypes")
	public List<ProtonPageUrl> pageForUser(Collection<GrantedAuthority> roles) {
		Reflections reflections = new Reflections(ProtonProjectApplication.class.getPackage().getName());
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
		List<ProtonPageUrl> pages = new ArrayList<ProtonPageUrl>();
		ProtonPageUrl pgDef = new ProtonPageUrl();
		pgDef.setUrl("/");
		pgDef.setUrlName("Home");
		pages.add(pgDef);
		for (Class cls : classes) {
			for (Method method : cls.getDeclaredMethods()) {
				Boolean checkOk = false;
				if (hasAnnotation(method)) {
					for (GrantedAuthority role : roles) {

						for (String val : method.getAnnotation(Secured.class).value()) {
							try {
								if (adminService.isActiveModule(method.getAnnotation(PageAnnotation.class).module())) {
									if (val.equals(role.getAuthority())) {
										checkOk = true;
									}
								}
							} catch (ModuleNotUndefinedException e) {
								e.printStackTrace();
							}
						}

					}
				}
				if (checkOk) {
					for (String val : method.getAnnotation(RequestMapping.class).value()) {
						ProtonPageUrl page = new ProtonPageUrl();
						page.setUrl(val);
						page.setUrlName(method.getAnnotation(PageAnnotation.class).value()[0]);
						pages.add(page);
					}

				}
			}
		}

		Collections.sort(pages);
		return pages;
	}

	public Boolean hasAnnotation(Method method) {
		for (Annotation an : method.getAnnotations()) {
			if (an.annotationType().getSimpleName().equals(PageAnnotation.class.getSimpleName())) {
				return true;
			}
		}
		return false;
	}

	public List<ProtonPageUrl> getAllPages() {
		Reflections reflections = new Reflections(ProtonProjectApplication.class.getPackage().getName());
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
		List<ProtonPageUrl> pages = new ArrayList<ProtonPageUrl>();
		ProtonPageUrl pgDef = new ProtonPageUrl();
		pgDef.setUrl("/");
		pgDef.setUrlName("Home");
		pages.add(pgDef);
		for (Class cls : classes) {
			for (Method method : cls.getDeclaredMethods()) {
				if (hasAnnotation(method)) {
					for (String val : method.getAnnotation(RequestMapping.class).value()) {
						ProtonPageUrl page = new ProtonPageUrl();
						page.setUrl(val);
						page.setUrlName(method.getAnnotation(PageAnnotation.class).value()[0]);
						pages.add(page);
					}
				}
			}
		}
		return pages;
	}
}
