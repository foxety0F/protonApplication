package com.foxety0f.proton.common.repository;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.database.DatabaseBeanFactory;
import com.foxety0f.proton.database.DatabaseNames;

@Repository
public class AppRoleDAO extends AbstractDAO{

	public AppRoleDAO(DataSource dataSource) {
		super(new DatabaseBeanFactory().createDataSource(DatabaseNames.CORE.name()));
	}
	
	public List<String> getRoleNames(Long userId){
		Map<String, Long> map = new HashedMap<String, Long>();
		map.put("userId", userId);
		
		return querySource.query("select role_name from USER_ROLE ur\r\n" + 
				"left join APP_ROLE ar on ur.role_id = ar.role_id"
				+ " where ur.user_id = :userId ", map, new StringRowMapper());
	}
	
}
