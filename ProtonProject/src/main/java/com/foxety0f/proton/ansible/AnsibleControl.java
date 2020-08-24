package com.foxety0f.proton.ansible;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import com.foxety0f.proton.modules.ProtonEssences;
import com.foxety0f.proton.modules.ProtonModules;

@Repository
public class AnsibleControl implements IAnsibleControl{
	
	private Map<String, AnsibleInformation> ansible = new LinkedHashMap<String,AnsibleInformation>();

	@Override
	public Map<String, AnsibleInformation> getAnsible() {
		return ansible;
	}

	@Override
	public void setAnsible(Map<String, AnsibleInformation> ansible) {
		this.ansible = ansible;
	}
	
	@Override
	public AnsibleInformation getAnsibleInformation(String guid) {
		return ansible.get(guid);
	}
	
	@Override
	public void setInfo(ProtonModules module, String guid, Object value, String method, ProtonEssences essence, 
			Date datetime, String description, String username) {
		Map<String, AnsibleInformation> setMap = new HashedMap<String, AnsibleInformation>();
		AnsibleInformation info = new AnsibleInformation();
		info.setValue(value);
		info.setDatetime(datetime);
		info.setDescription(description);
		info.setEssence(essence);
		info.setMethod(method);
		info.setUsername(username);
		info.setModule(module);
		
		ansible.put(guid, info);
	}
	
	@Override
	public void setValue(String guid, Object value) {
		ansible.get(guid).setValue(value);
	}
	
	@Override
	public void setDatetime(String guid, Date datetime) {
		ansible.get(guid).setDatetime(datetime);
	}
	
	@Override
	public void setDescription(String guid, String description) {
		ansible.get(guid).setDescription(description);
	}
	
	@Override
	public void setEssence(String guid, ProtonEssences essence) {
		ansible.get(guid).setEssence(essence);
	}
	
	@Override
	public void setMethod(String guid, String method) {
		ansible.get(guid).setMethod(method);
	}
	
	@Override
	public void setUsername(String guid, String username) {
		ansible.get(guid).setUsername(username);
	}
	
	@Override
	public void setModule(String guid, ProtonModules module) {
		ansible.get(guid).setModule(module);
	}
	
	@Override
	public String generateGuid() {
		UUID uuid = UUID.randomUUID();
		
		String guid = uuid.toString();
		
		return guid;
	}
	
	@Override
	public String geenrateGuid(ProtonModules module) {
		UUID uuid = UUID.randomUUID();
		
		String guid = module.moduleName().substring(0,3) + uuid.toString().substring(3);
		
		return guid;
	}
	
	@Override
	public void kill(String guid) {
		ansible.remove(guid);
	}
	
	public void addInfo(String guid, AnsibleInformation info) {
		ansible.put(guid, info);
	}
	
	public void killByUserAndEssence(String username, ProtonEssences essence) {
		Set<String> keys = getAnsible().keySet();
		
		for(String key : keys) {
			if(getAnsible().get(key).getUsername().equals(username) && getAnsible().get(key).getEssence() == essence) {
				kill(key);
			}
		}
	}

}
