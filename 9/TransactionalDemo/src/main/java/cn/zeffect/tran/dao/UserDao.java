package cn.zeffect.tran.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbc;

	public boolean removeMoney(String userid, double money) {
		String sql = "select money from user where userid = ?";
		Map<String, Object> data = jdbc.queryForMap(sql, new Object[] { userid });
		double userMoney = (double) data.get("money");
		if (userMoney < money) {
			throw new RuntimeException();
		}
		userMoney = userMoney - money;
		String updateSql = "update user set money = ? where userid = ?";
		return jdbc.update(updateSql, new Object[] { userMoney, userid }) > 0;
	};

	public boolean addMoney(String userid, double money) {
		String sql = "select money from user where userid = ?";
		Map<String, Object> data = jdbc.queryForMap(sql, new Object[] { userid });
		double userMoney = (double) data.get("money");
		userMoney = userMoney + money;
		String updateSql = "update user set money = ? where userid = ?";
		return jdbc.update(updateSql, new Object[] { userMoney, userid }) > 0;
	}

}
