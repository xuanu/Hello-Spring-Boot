package cn.zeffect.jdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zeffect.jdbc.dao.UserDao;
import cn.zeffect.jdbc.model.ResultInfo;
import cn.zeffect.jdbc.model.User;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	public UserDao userDao;

	@RequestMapping("/getUser")
	public ResultInfo getUser(@RequestParam(name = "userid") String userid) {
		User tmpUser = userDao.getUser(userid);
		if (tmpUser == null) {
			return new ResultInfo(-1, "用户不存在");
		} else {
			return new ResultInfo(1, "获取用户信息成功", tmpUser);
		}
	}

	@RequestMapping("/getUsers")
	public List<User> getUsers() {
		return userDao.getUsers();
	}

}
