package com.foxety0f.proton.ansible;

import java.util.Date;
import java.util.Map;

import com.foxety0f.proton.modules.ProtonEssences;
import com.foxety0f.proton.modules.ProtonModules;

public interface IAnsibleControl {

	Map<String, AnsibleInformation> getAnsible();

	void setAnsible(Map<String, AnsibleInformation> ansible);

	AnsibleInformation getAnsibleInformation(String guid);

	void setInfo(ProtonModules module, String guid, Object value, String method, ProtonEssences essence, Date datetime,
			String description, String username);

	void setValue(String guid, Object value);

	void setDatetime(String guid, Date datetime);

	void setDescription(String guid, String description);

	void setEssence(String guid, ProtonEssences essence);

	void setMethod(String guid, String method);

	void setUsername(String guid, String username);

	void setModule(String guid, ProtonModules module);

	String generateGuid();

	String geenrateGuid(ProtonModules module);

	void kill(String guid);

}