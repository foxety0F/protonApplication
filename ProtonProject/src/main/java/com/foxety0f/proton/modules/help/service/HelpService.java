package com.foxety0f.proton.modules.help.service;

import java.util.List;

import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.help.dao.IHelpDao;
import com.foxety0f.proton.modules.help.domain.HelpInformation;

public class HelpService implements IHelpService {

	private IHelpDao helpDao;

	public HelpService(IHelpDao helpDao) {
		this.helpDao = helpDao;
	}

	public void createNewHelp(String helpName, String helpDescription, String url, Integer refTypeId, String helpText,
			UserDetailsProton user) {
		helpDao.createNewHelp(helpName, helpDescription, url, refTypeId, helpText, user);
	}

	public void updateHelp(Integer helpId, String helpName, String helpDescription, String url, Integer refTypeId,
			String helpText, UserDetailsProton user) {
		helpDao.updateHelp(helpId, helpName, helpDescription, url, refTypeId, helpText, user);
	}

	public void updateHelp(Integer helpId, String helpText) {
		helpDao.updateHelp(helpId, helpText);
	}

	public HelpInformation getHelp(String requestUrl) {
		return helpDao.getHelp(requestUrl);
	}

	public HelpInformation getHelp(Integer helpId) {
		return helpDao.getHelp(helpId);
	}

	public HelpInformation getHelpReport(Integer reportId) {
		return helpDao.getHelpReport(reportId);
	}

	public HelpInformation getHelpConstructor(Integer reportConstrId) {
		return helpDao.getHelpConstructor(reportConstrId);
	}

	public List<HelpInformation> getAllHelps() {
		return helpDao.getAllHelps();
	}

	@Override
	public void updateHelp(Integer helpId, String helpText, UserDetailsProton user) {
		helpDao.updateHelp(helpId, helpText, user);
	}
}
