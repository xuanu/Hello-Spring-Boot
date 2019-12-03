package cn.zeffect.thy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.zeffect.thy.bean.User;

@RestController
public class ThyController {

	@RequestMapping("/index")
	public ModelAndView testJsp() {
		ModelAndView view = new ModelAndView();
		view.setViewName("index");
		view.addObject("username", "郑治玄");
		//
		view.addObject("stylename", "<span style='color:red'>郑治玄</span>");
		//
		User my = new User();
		my.setAge(27);
		my.setName("郑治玄");
		view.addObject("user", my);// 传递的对象必须序列化
		// List
		List<User> userList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setAge(new Random().nextInt(100));
			user.setName("名字" + new Random().nextInt(100));
			userList.add(user);
		}
		view.addObject("users", userList);
		// Map
		Map<String, User> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setAge(new Random().nextInt(100));
			user.setName("名字" + new Random().nextInt(100));
			map.put("key" + new Random().nextInt(100), user);
		}
		view.addObject("usermap", map);
		return view;
	}

	@RequestMapping("/href")
	public ModelAndView href() {
		ModelAndView view = new ModelAndView();
		view.setViewName("href");
		return view;
	}
}
