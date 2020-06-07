package com.foxety0f.proton.modules.hire.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.jdbc.core.RowMapper;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredConfig;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredExperience;
import com.foxety0f.proton.modules.hire.domain.EmployeeHiredSkills;
import com.foxety0f.proton.modules.hire.domain.HiredSkills;

public class HiredDAO extends AbstractDAO implements IHiredDAO {

	public HiredDAO(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<EmployeeHiredConfig> getEmployeeHiredConfig() {
		List<EmployeeHiredConfig> result;

		String sql = "		select hc.id brief_id\r\n" + "			 , au.user_id\r\n"
				+ "			 , au.first_name\r\n" + "			 , au.surname\r\n" + "			 , hc.user_phone\r\n"
				+ "			 , null title_name\r\n" + "			 , hc.about\r\n"
				+ "		  from proton_hired_config hc\r\n" + "	 left join app_user au on hc.user_id = au.user_id	";

		result = querySource.query(sql, new EmployeeHiredConfigRowMapper());

		return result;
	}
	
	@Override
	public List<EmployeeHiredConfig> getEmployeeHiredConfig(Integer userId) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("userId", userId);
		
		List<EmployeeHiredConfig> result;

		String sql = "		select hc.id brief_id\r\n" + "			 , au.user_id\r\n"
				+ "			 , au.first_name\r\n" + "			 , au.surname\r\n" + "			 , hc.user_phone\r\n"
				+ "			 , null title_name\r\n" + "			 , hc.about\r\n"
				+ "		  from proton_hired_config hc\r\n" + "	 left join app_user au on hc.user_id = au.user_id	"
						+ " where hc.user_id = :userId";

		result = querySource.query(sql, new EmployeeHiredConfigRowMapper());

		return result;
	}

	private class EmployeeHiredConfigRowMapper implements RowMapper<EmployeeHiredConfig> {

		@Override
		public EmployeeHiredConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeHiredConfig item = new EmployeeHiredConfig();
			item.setUserId(rs.getString("user_id"));
			item.setFirstName(rs.getString("first_name"));
			item.setSurname(rs.getString("surname"));
			item.setUserPhone(rs.getString("user_phone"));
			item.setCurrentTitle(rs.getString("title_name"));
			item.setAbout(rs.getString("about"));
			item.setRowId(rs.getInt("brief_id"));
			return item;
		}

	}

	@Override
	public String getAbout(Integer briefId) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("briefId", briefId);
		return querySource.queryForObject("select about from proton_hired_config where id = :briefId", map,
				String.class);
	}

	@Override
	public String getPhone(Integer briefId) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("briefId", briefId);
		return querySource.queryForObject("select user_phone from proton_hired_config where id = :briefId", map,
				String.class);
	}

	@Override
	public List<EmployeeHiredExperience> getEmployeeExperience(Integer briefId) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("briefId", briefId);

		return querySource.query("		select he.id experience_id\r\n" + "			 , he.title_name\r\n"
				+ "			 , he.description\r\n" + "			 , he.start_date\r\n" + "			 , he.end_date\r\n"
				+ "			 , he.skill_points\r\n" + "			 , he.brief_id\r\n" + "			 , he.order_num\r\n"
				+ "			 , he.is_current\r\n" + "		  from proton_hired_config hc\r\n"
				+ "	 left join proton_hired_experience he on hc.id = he.brief_id" + " where he.brief_id = :briefId",
				new EmployeeHiredExperienceRowMapper());
	}
	
	@Override
	public List<EmployeeHiredExperience> getSelfEmployeeExperience(Integer userId) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("userId", userId);

		return querySource.query("		select he.id experience_id\r\n" + "			 , he.title_name\r\n"
				+ "			 , he.description\r\n" + "			 , he.start_date\r\n" + "			 , he.end_date\r\n"
				+ "			 , he.skill_points\r\n" + "			 , he.brief_id\r\n" + "			 , he.order_num\r\n"
				+ "			 , he.is_current\r\n" + "		  from proton_hired_config hc\r\n"
				+ "	 left join proton_hired_experience he on hc.id = he.brief_id" + " where hс.id = (select max(id) from proton_hired_config where user_id = :userId)"
						+ " order by he.id desc",
				new EmployeeHiredExperienceRowMapper());
	}

	private class EmployeeHiredExperienceRowMapper implements RowMapper<EmployeeHiredExperience> {

		@Override
		public EmployeeHiredExperience mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeHiredExperience item = new EmployeeHiredExperience();
			item.setBriefId(rs.getInt("brief_id"));
			item.setExperienceId(rs.getInt("experience_id"));
			item.setTitleName(rs.getString("title_name"));
			item.setDescription(rs.getString("description"));
			item.setStartDate(rs.getDate("start_date"));
			item.setEndDate(rs.getDate("end_date"));
			item.setSkillPoints(rs.getString("skill_points"));
			item.setOrderId(rs.getInt("order_num"));
			item.setCurrent(rs.getInt("is_current") == 0 ? false : true);
			return item;
		}

	}
	
	@Override
	public List<EmployeeHiredSkills> getEmployeeSkills(Integer briefId){
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("briefId", briefId);
		
		return querySource.query("		select hus.skill_id\r\n" + 
				"			 , hus.value\r\n" + 
				"			 , hus.purpose\r\n" + 
				"			 , hs.skill_name\r\n" + 
				"			 , hs.skill_description\r\n" + 
				"			 , hs.skill_min_scale\r\n" + 
				"			 , hs.skill_max_scale\r\n" + 
				"		  from proton_hired_user_skills hus\r\n" + 
				" 	 left join proton_hired_skills hs on hus.skill_id = hs.id"
				+ " where hus.brief_id = :briefId", new EmployeeHiredSkillsRowMapper());
	}
	
	@Override
	public List<EmployeeHiredSkills> getSelfEmployeeSkills(Integer userId){
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("userId", userId);
		
		return querySource.query("		select hus.skill_id\r\n" + 
				"			 , hus.value\r\n" + 
				"			 , hus.purpose\r\n" + 
				"			 , hs.skill_name\r\n" + 
				"			 , hs.skill_description\r\n" + 
				"			 , hs.skill_min_scale\r\n" + 
				"			 , hs.skill_max_scale\r\n" + 
				"		  from proton_hired_user_skills hus\r\n" + 
				" 	 left join proton_hired_skills hs on hus.skill_id = hs.id"
				+ "	 left join proton_hired_config hc on hus.brief_id = hc.brief_id "
				+ " where hс.id = (select max(id) from proton_hired_config where user_id = :userId)"
				+ " order by hc.id desc", new EmployeeHiredSkillsRowMapper());
	}
	
	private class EmployeeHiredSkillsRowMapper implements RowMapper<EmployeeHiredSkills>{

		@Override
		public EmployeeHiredSkills mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeHiredSkills item = new EmployeeHiredSkills();
			item.setSkillId(rs.getInt("skill_id"));
			item.setValue(rs.getString("value"));
			item.setPurpose(rs.getString("purpose"));
			item.setSkillName(rs.getString("skill_name"));
			item.setSkillDescription(rs.getString("skill_description"));
			item.setSkillMinScale(rs.getInt("skill_min_scale"));
			item.setSkillMaxScale(rs.getInt("skill_max_scale"));
			
			return item;
		}
		
	}
	
	public List<HiredSkills> getHiredSkills(){
		return querySource.query("SELECT id, skill_name, skill_description, skill_min_scale, skill_max_scale\r\n" + 
				"	FROM public.proton_hired_skills;", new HiredSkillsRowMapper());
	}
	
	private class HiredSkillsRowMapper implements RowMapper<HiredSkills>{

		@Override
		public HiredSkills mapRow(ResultSet rs, int rowNum) throws SQLException {
			HiredSkills item = new HiredSkills();
			item.setId(rs.getInt(1));
			item.setSkillName(rs.getString(2));
			item.setSkillDescript(rs.getString(3));
			item.setSkillMinScale(rs.getInt(4));
			item.setSkillMaxScale(rs.getInt(5));
			return item;
		}
		
	}
	
	@Override
	public Integer getUserId(Integer briefId) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("briefId", briefId);
		return querySource.queryForObject("select user_id from proton_hired_config where id = :briefId", map,
				Integer.class);
	}


}
