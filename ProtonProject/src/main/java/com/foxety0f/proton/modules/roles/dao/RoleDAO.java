package com.foxety0f.proton.modules.roles.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.jdbc.core.RowMapper;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.common.exceptions.UserNotFound;
import com.foxety0f.proton.common.user.AppUser;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonEssences;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.roles.domain.ProtonRole;
import com.foxety0f.proton.modules.roles.domain.ProtonRoleSets;
import com.foxety0f.proton.modules.roles.domain.ProtonUserRole;

public class RoleDAO extends AbstractDAO implements IRoleDAO {

	public RoleDAO(DataSource dataSource) {
		super(dataSource);
	}

	private ProtonModules thisModule = ProtonModules.ROLES;

	public List<ProtonRole> getProtonRoles() {
		return querySource.query("select role_id, role_name, null role_description from app_role",
				new ProtonRoleRowMapper());
	}

	private static class ProtonRoleRowMapper implements RowMapper<ProtonRole> {

		@Override
		public ProtonRole mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProtonRole item = new ProtonRole();

			item.setId(rs.getInt("role_id"));
			item.setRoleName(rs.getString("role_name"));
			item.setDescription(rs.getString("role_description"));

			return item;
		}

	}

	@Override
	public List<ProtonUserRole> getProtonRoleUser() {
		return querySource.query("with roles as(\r\n"
				+ "select distinct ar.role_name, au.user_name, ar.role_id, au.user_id from app_role ar\r\n"
				+ "	 cross join app_user au)\r\n" + "	 select roles.role_name \r\n" + "	      , roles.user_name\r\n"
				+ "		  , roles.role_id\r\n" + "		  , roles.user_id\r\n"
				+ "		  , case when ur.id is not null then 1 else 0 end hasRole\r\n" + "	   from roles\r\n"
				+ "  left join user_role ur on roles.user_id = ur.user_id and roles.role_id = ur.role_id\r\n"
				+ "   order by roles.user_name, roles.role_id\r\n" + "	 ", new ProtonUserRoleRowMapper());
	}

	@Override
	public List<ProtonUserRole> getProtonRoleUser(String userName) throws UserNotFound {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("username", userName);

		Integer cntUser = querySource.queryForObject("select count(*) from app_user " + " where user_name = :username",
				map, Integer.class);

		if (cntUser == 0) {
			logException(thisModule, ProtonEssences.USER_NOT_FOUND_EXCEPTION, "UserNotFound", userName);
			throw new UserNotFound(userName);
		}

		return querySource.query("with roles as(\r\n"
				+ "select distinct ar.role_name, au.user_name, ar.role_id, au.user_id from app_role ar\r\n"
				+ "	 cross join app_user au" + " where au.user_name = :username)\r\n"
				+ "	 select roles.role_name \r\n" + "	      , roles.user_name\r\n" + "		  , roles.role_id\r\n"
				+ "		  , roles.user_id\r\n" + "		  , case when ur.id is not null then 1 else 0 end hasRole\r\n"
				+ "	   from roles\r\n"
				+ "  left join user_role ur on roles.user_id = ur.user_id and roles.role_id = ur.role_id\r\n"
				+ "   order by roles.user_name, roles.role_id\r\n", new ProtonUserRoleRowMapper());
	}

	public void setProtonRoles(List<ProtonRoleSets> setter, UserDetailsProton user) {
		if (!setter.isEmpty()) {
			List<Integer> idList = new ArrayList<Integer>();
			for (ProtonRoleSets set : setter) {
				idList.add(set.getUserId());
			}

			idList = idList.stream().distinct().collect(Collectors.toList());

			for (Integer id : idList) {
				querySource.update("delete from user_role where user_id = " + id, EMPTY_PARAMS);
				for (ProtonRoleSets set : setter) {
					if (set.getUserId().equals(id)) {
						Map<String, Integer> map = new HashedMap<String, Integer>();
						map.put("id", id);
						map.put("roleId", set.getRoleId());
						querySource.update("insert into user_role(user_id, role_id) values (:id, :roleId)", map);
						loggerWithUser(thisModule, map, ProtonEssences.INSERT, user);
					}
				}
			}
		}
	}

	private static class ProtonUserRoleRowMapper implements RowMapper<ProtonUserRole> {

		@Override
		public ProtonUserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProtonUserRole item = new ProtonUserRole();

			item.setIdRole(rs.getInt("role_id"));
			item.setRoleName(rs.getString("role_name"));
			item.setUserId(rs.getLong("user_id"));
			item.setUsername(rs.getString("user_name"));
			item.setHasRole(rs.getInt("hasRole") == 1 ? true : false);

			return item;
		}

	}

	public void createNewRole(String roleName) {
		Integer role_id = querySource.queryForObject("select max(role_id) from app_role", EMPTY_PARAMS, Integer.class);
		role_id = role_id + 1;

		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("roleId", role_id);
		map.put("roleName", roleName);

		querySource.update("insert into app_role values(:roleId, :roleName)", map);
		logger(thisModule, map, ProtonEssences.INSERT, null);
	}

	public void createNewRole(String roleName, UserDetailsProton user) {
		Integer role_id = querySource.queryForObject("select max(role_id) from app_role", EMPTY_PARAMS, Integer.class);
		role_id = role_id + 1;

		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("roleId", role_id);
		map.put("roleName", roleName);

		querySource.update("insert into app_role values(:roleId, :roleName)", map);
		loggerWithUser(thisModule, map, ProtonEssences.INSERT, user);
	}

	public List<AppUser> getAllUsers() {
		List<AppUser> rs = querySource.query("select * from APP_USER ", EMPTY_PARAMS, new AppUserRowMapper());

		return rs;

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
	
	public void addRoleToUser(Integer roleId, Integer userId, UserDetailsProton user) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		
		querySource.update("insert into user_role(user_id, role_id) values(:userId, :roleId)", map);
		
		loggerWithUser(thisModule, map, ProtonEssences.ADD_ROLE, user);
	}
	
	public void removeRoleFromUser(Integer roleId, Integer userId, UserDetailsProton user) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		
		querySource.update("delete from user_role where user_id = :userId and role_id = :roleId", map);
		
		loggerWithUser(thisModule, map, ProtonEssences.REMOVE_ROLE, user);
	}

}
