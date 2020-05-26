package com.foxety0f.proton.common.abstracts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.foxety0f.proton.common.domain.ProtonPageUrl;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.common.user.UserDetailsServiceImpl;
import com.foxety0f.proton.modules.admin.service.AdminService;
import com.foxety0f.proton.utils.JsonUtils;

public abstract class AbstractController {

	@Autowired
	private UserDetailsServiceImpl userService;

	@Autowired
	private AdminService adminService;

	public enum MessagesType {
		FIELDS_ERRORS, GLOBAL_ERRORS, INFO_MESSAGES
	}

	private static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);

	protected String toJson(Object data) {
		return JsonUtils.toJson(data);
	}

	public List<ProtonPageUrl> getAllPages() {
		return userService.getAllPages();
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void udateMenuList(UserDetailsProton user) {
		Integer cntForReload = user.getCountForReload();
		if (cntForReload <= 1) {
			user.setCountForReload(10);
			user.setPages(userService.pageForUser(user.getAuthorities()));
		} else {
			user.setCountForReload(cntForReload - 1);
		}
	}
	
	public void updateMenuListNow(UserDetailsProton user) {
		user.setCountForReload(10);
		user.setPages(userService.pageForUser(user.getAuthorities()));
	}

}
