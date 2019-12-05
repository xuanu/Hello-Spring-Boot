package cn.zeffect.thy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.zeffect.thy.bean.User;
import cn.zeffect.thy.utils.TextUtils;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbc;

	/**
	 * 用户名是否存在
	 * 
	 * @param user
	 * @return
	 */
	public boolean isUserExist(String user) {
		if (TextUtils.isEmpty(user)) {
			throw new NullPointerException("用户名不能为空!");
		}
		User myUser = getUser(user);
		return myUser != null;
	}

	/**
	 * 获取用户信息，根据用户名
	 * 
	 * @param user
	 * @return
	 */
	public User getUser(String user) {
		if (TextUtils.isEmpty(user)) {
			return null;
		}
		String sql = "select * from user_info where user = ?";
		User myUser = null;
		try {
			myUser = jdbc.queryForObject(sql, new Object[] { user }, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User tmpUser = new User();
					tmpUser.setUser(rs.getString("user"));
					tmpUser.setUserid(rs.getString("userid"));
					return tmpUser;
				}

			});
		} catch (Exception e) {
		}
		return myUser;
	}

	/***
	 * 获取用户，根据用户id
	 * 
	 * @param userid
	 * @return
	 */
	public User getUserWithId(String userid) {
		if (TextUtils.isEmpty(userid)) {
			return null;
		}
		String sql = "select * from user_info where userid = ?";
		User myUser = null;
		try {
			myUser = jdbc.queryForObject(sql, new Object[] { userid }, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User tmpUser = new User();
					tmpUser.setUser(rs.getString("user"));
					tmpUser.setUserid(rs.getString("userid"));
					return tmpUser;
				}

			});
		} catch (Exception e) {
		}
		return myUser;
	}

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @param pw
	 * @return
	 */
	public User saveUser(String user, String pw) {
		if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pw)) {
			return null;
		}
		String userid = UUID.randomUUID().toString();
		String sql = "insert into user_info(user,pw,userid) values (?,?,?)";
		if (jdbc.update(sql, new Object[] { user, pw, userid }) > 0) {
			User myUser = new User();
			myUser.setUser(user);
			myUser.setUserid(userid);
			return myUser;
		} else {
			return null;
		}
	}

}
