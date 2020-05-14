package com.foxety0f.proton.common.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.common.user.AppUser;
import com.foxety0f.proton.database.DatabaseBeanFactory;
import com.foxety0f.proton.database.DatabaseNames;

@Repository
public class AppUserDAO extends AbstractDAO{

	public AppUserDAO(DataSource dataSource) {
		super(new DatabaseBeanFactory().createDataSource(DatabaseNames.CORE.name()));
	}

	
	public AppUser findUserAccount(String userName) {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("userName", userName);
		
		
			List<AppUser> rs = querySource.query("select * from APP_USER "
					+ " where user_name = :userName", map, new AppUserRowMapper());
			
		if(rs.size() > 0)
			return rs.get(0);
		
		return null;
		
	} 
	
	private static class AppUserRowMapper implements RowMapper<AppUser> {

		@Override
		public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			AppUser item = new AppUser();

			item.setUserId(rs.getLong("user_id"));
			item.setUserName(rs.getString("user_name"));
			item.setEncryptedPassword(rs.getString("encryted_password"));
			item.setEnabled(rs.getInt("enabled") == 1 ? true : false);
			item.setFirstName(rs.getString("first_name"));
			item.setSurname(rs.getString("surname"));
			item.setIsFirstTime(rs.getInt("is_first_time") == 0 ? true : false);
			
			return item;
		}

	}
}
