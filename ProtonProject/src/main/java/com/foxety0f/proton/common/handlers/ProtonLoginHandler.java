package com.foxety0f.proton.common.handlers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.foxety0f.proton.common.user.AuthenticatedUserLog;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.admin.service.IAdminService;

public class ProtonLoginHandler extends SimpleUrlLogoutSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	private IAdminService adminService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		AuthenticatedUserLog ul = new AuthenticatedUserLog();
		ul.setGrantList((List<GrantedAuthority>) authentication.getAuthorities());;
		ul.setTsAuth(new Date().getTime());
		ul.setTsLastAction(new Date().getTime());
		ul.setUserName(authentication.getName());
		
		UserDetailsProton user = (UserDetailsProton) authentication.getPrincipal();
		
		ul.setUserId(user.getUserId());
		adminService.removeAuth(authentication.getName());

		adminService.addAuth(ul);
		
		response.sendRedirect("/");
		
	}

}
