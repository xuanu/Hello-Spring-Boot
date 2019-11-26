package cn.zeffect.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.zeffect.jdbc.model.User;

@Repository // Dao需要使用@Repository注解
public class UserDao {
	@Resource // 可以通过Resource注解，也可以通过@Autowired注解
	private JdbcTemplate jdbc;

	/**
	 * 根据Userid查询某人信息
	 * 
	 * @param userid
	 * @return
	 */
	public User getUser(String userid) {
		String sql = "select * from user where userid = ?";
		try {// 当没有记录时，会报错，所以通过try catch来拦截
			return jdbc.queryForObject(sql, new Object[] { userid }, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User tmpUser = new User();
					tmpUser.setUser(rs.getString("user"));// 这里ResultSet里的user是表字段的名字。
					tmpUser.setUserid(rs.getString("userid"));// userid也是
					return tmpUser;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}

	public List<User> getUsers() {
		String sql = "select * from user";
		return jdbc.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User tmpUser = new User();
				tmpUser.setUser(rs.getString("user"));// 这里ResultSet里的user是表字段的名字。
				tmpUser.setUserid(rs.getString("userid"));// userid也是
				return tmpUser;
			}
		});
	}

}
