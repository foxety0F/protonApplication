package com.foxety0f.proton.common.handlers;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.foxety0f.proton.modules.admin.service.AdminService;

public class ProtonLogoutHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler{

	@Autowired
	private AdminService adminService;
	
	protected final static Logger LOGGER = LoggerFactory.getLogger(ProtonLogoutHandler.class);
	
	@Override
	public void onLogoutSuccess(
		      HttpServletRequest request, 
		      HttpServletResponse response, 
		      Authentication authentication) 
		      throws IOException, ServletException {
		Enumeration<String> headers =  request.getHeaderNames();

		System.err.println(request.getAttribute("protonTypeUser"));
		
		try {
			adminService.removeAuth(authentication.getName());
		}catch(NullPointerException e) {
			LOGGER.warn("User try to logout but authentication not found");
		}
		
		response.sendRedirect("/");
	}
	
}
