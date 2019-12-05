package cn.zeffect.thy.service;

import javax.annotation.Resource;
import javax.swing.plaf.TextUI;

import org.springframework.stereotype.Service;

import cn.zeffect.thy.bean.ResultInfo;
import cn.zeffect.thy.bean.User;
import cn.zeffect.thy.dao.UserDao;
import cn.zeffect.thy.utils.TextUtils;

@Service
public class UserService {

	@Resource
	private UserDao userDao;

	public ResultInfo register(String user, String pw) {
		if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pw)) {
			return new ResultInfo(-1, "用户名或密码为空");
		}
		if (user.length() > 255) {
			return new ResultInfo(-1, "用户名过长！");
		}
		if (pw.length() > 255) {
			return new ResultInfo(-1, "密码过长！");
		}
		if (userDao.isUserExist(user)) {
			return new ResultInfo(-1, "用户名已注册！");
		}
		User myUser = userDao.saveUser(user, pw);
		if (myUser == null) {
			return new ResultInfo(-1, "用户注册失败！");
		}
		return new ResultInfo(1, "注册成功", myUser);
	}

	public ResultInfo login(String user, String pw) {
		if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pw)) {
			return new ResultInfo(-1, "用户名或密码为空");
		}
		User myUser = userDao.getUser(user);
		if (myUser == null) {
			return new ResultInfo(-1, "用户未注册！");
		}
		return new ResultInfo(1, "登录成功", myUser);
	}

	/**
	 * 获取用户
	 * 
	 * @param userid
	 * @return
	 */
	public ResultInfo getUser(String userid) {
		if (TextUtils.isEmpty(userid)) {
			return new ResultInfo(-1, "用户ID为空");
		}
		User myUser = userDao.getUserWithId(userid);
		if (myUser == null) {
			return new ResultInfo(-1, "用户不存在");
		} else {
			return new ResultInfo(1, "获取用户成功", myUser);
		}
	}

}
