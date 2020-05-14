package com.foxety0f.proton.modules.help.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.jdbc.core.RowMapper;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.common.user.UserDetailsProton;
import com.foxety0f.proton.modules.ProtonEssences;
import com.foxety0f.proton.modules.ProtonModules;
import com.foxety0f.proton.modules.help.domain.HelpInformation;

public class HelpDao extends AbstractDAO implements IHelpDao {

	public HelpDao(DataSource dataSource) {
		super(dataSource);
	}

	ProtonModules thisModule = ProtonModules.HELP;

	public void createNewHelp(String helpName, String helpDescription, String url, Integer refTypeId, String helpText,
			UserDetailsProton user) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("helpName", helpName);
		map.put("helpDescription", helpDescription);
		map.put("url", url);
		map.put("refTypeId", refTypeId);
		map.put("helpText", helpText);

		querySource.update(
				"insert into proton_help_config(help_name, help_description, request_url, ref_type_id, help_text) "
						+ " values(:helpName, :helpDescription, :url, :refTypeId, :helpText)",
				map);

		//loggerWithUser(thisModule, map, ProtonEssences.INSERT, user);
	}

	public void updateHelp(Integer helpId, String helpName, String helpDescription, String url, Integer refTypeId,
			String helpText, UserDetailsProton user) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("helpId", helpId);
		map.put("helpName", helpName);
		map.put("helpDescription", helpDescription);
		map.put("refTypeId", refTypeId);
		map.put("helpText", helpText);

		querySource.update("update proton_help_config" + " set help_name = :helpName ,"
				+ " help_description = :helpDescription,  ref_type_id = :refTypeId,"
				+ " help_text = :helpText" + " where id = :helpId", map);
	}

	public void updateHelp(Integer helpId, String helpText) {
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("helpId", helpId);
		map.put("helpText", helpText);

		querySource.update("update proton_help_config" + " set " + " help_text = :helpText" + " where id = :helpId",
				map);
	}

	public HelpInformation getHelp(String requestUrl) {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("url", requestUrl);
		
		List<HelpInformation> result = querySource.query(
				"select id helpId, help_name helpName, help_description helpDescription, request_url url, help_text helpText from proton_help_config where request_url = :url",
				map, new HelpInformationRowMapper());

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public HelpInformation getHelp(Integer helpId) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("helpId", helpId);

		List<HelpInformation> result = querySource.query(
				"select id helpId, help_name helpName, help_description helpDescription,request_url url, help_text helpText from proton_help_config where help_id = :helpId",
				map, new HelpInformationRowMapper());

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
		
	}

	public HelpInformation getHelpReport(Integer reportId) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("reportId", reportId);

		List<HelpInformation> result = querySource.query(
				"select id helpId, help_name helpName, help_description helpDescription, request_url url, help_text helpText from proton_help_config where report_id = :reportId",
				map, new HelpInformationRowMapper());

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public HelpInformation getHelpConstructor(Integer reportConstrId) {
		Map<String, Integer> map = new HashedMap<String, Integer>();
		map.put("constrId", reportConstrId);

		List<HelpInformation> result = querySource.query(
				"select id helpId, help_name helpName, help_description helpDescription, request_url url,help_text helpText from proton_help_config where report_constr_id = :constrId",
				map, new HelpInformationRowMapper());

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}
	
	public List<HelpInformation> getAllHelps() {
		List<HelpInformation> result = querySource.query(
				"select id helpId, help_name helpName, help_description helpDescription, request_url url, help_text helpText from proton_help_config",
				EMPTY_PARAMS, new HelpInformationRowMapper());

		return result;
	}

	private static class HelpInformationRowMapper implements RowMapper<HelpInformation> {

		@Override
		public HelpInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
			HelpInformation item = new HelpInformation();

			item.setHelpId(rs.getInt("helpId"));
			item.setHelpName(rs.getString("helpName"));
			item.setHelpDescription(rs.getString("helpDescription"));
			item.setHelpText(rs.getString("helpText"));
			item.setUrl(rs.getString("url"));
			return item;
		}

	}

}
