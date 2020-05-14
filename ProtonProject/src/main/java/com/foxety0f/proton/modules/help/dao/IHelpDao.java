package com.foxety0f.proton.modules.help.dao;

import java.util.List;

import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.help.domain.HelpInformation;

public interface IHelpDao {
	void createNewHelp(String helpName, String helpDescription, String url, Integer refTypeId, String helpText,
			UserDetailsProton user);

	void updateHelp(Integer helpId, String helpName, String helpDescription, String url, Integer refTypeId,
			String helpText, UserDetailsProton user);

	void updateHelp(Integer helpId, String helpText);

	HelpInformation getHelp(String requestUrl);

	HelpInformation getHelp(Integer helpId);

	HelpInformation getHelpReport(Integer reportId);

	HelpInformation getHelpConstructor(Integer reportConstrId);

	List<HelpInformation> getAllHelps();
}
