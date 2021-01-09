package com.foxety0f.proton.modules.education.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.jdbc.core.RowMapper;

import com.foxety0f.proton.common.abstracts.AbstractDAO;
import com.foxety0f.proton.modules.education.domain.TempTestData;
import com.foxety0f.proton.modules.education.domain.TestApi;

public class EducationDao extends AbstractDAO implements IEducationDao {

    public EducationDao(DataSource dataSource) {
	super(dataSource);
    }

    private Integer cnt = 0;

    public Integer getCnt() {
	return cnt;
    }

    @Override
    public void setCnt(Integer cnt) {
	this.cnt = cnt;
    }

    @Override
    public List<TestApi> getTestApi() {

	String sql = "select * from test_api";

	return querySource.query(sql, new TestApiRowMapper());
    }

    public void setTestApi(Integer id, String name, Date date, Boolean isActual) {
	Map<String, Object> map = new HashedMap<String, Object>();
	map.put("id", id);
	map.put("name", name);
	map.put("date", date);
	map.put("isActual", isActual == true ? 1 : 0);

	querySource.update("insert into test_api values (:id, :name, :date, :isActual)", map);
    }

    private static class TestApiRowMapper implements RowMapper<TestApi> {

	@Override
	public TestApi mapRow(ResultSet rs, int rowNum) throws SQLException {
	    TestApi item = new TestApi();
	    item.setId(rs.getInt("id"));
	    item.setName(rs.getString("name"));
	    item.setDatetime(rs.getDate("datetime"));
	    item.setIsActual(rs.getInt("is_actual") == 1 ? true : false);
	    return item;
	}

    }

    public List<TempTestData> getTempTestData() {

	String sql = "select * from temp_test_data1";

	return querySource.query(sql, new TempTestDataRowMapper());
    }

    private static class TempTestDataRowMapper implements RowMapper<TempTestData> {

	@Override
	public TempTestData mapRow(ResultSet rs, int rowNum) throws SQLException {

	    TempTestData item = new TempTestData();

	    item.setId(rs.getInt("id"));
	    item.setName(rs.getString("name"));
	    item.setGroupOne(rs.getString("group_one"));
	    item.setGroupTwo(rs.getString("group_two"));
	    item.setDateAdd(rs.getDate("date_add"));
	    item.generateToString();
	    item.hashCodeGroupOne();
	    item.hashCodeGroupTwo();

	    return item;
	}

    }

}
