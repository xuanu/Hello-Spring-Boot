package cn.zeffect.thy.controller;

import java.time.Duration;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.zeffect.thy.bean.ResultInfo;
import cn.zeffect.thy.bean.User;
import cn.zeffect.thy.filter.LoginInterceptor;
import cn.zeffect.thy.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	@Autowired
	private RedisTemplate<String, String> redis;

	@RequestMapping("/register.ht")
	public ModelAndView register() {
		ModelAndView view = new ModelAndView();
		view.setViewName("register");
		return view;
	}

	@RequestMapping("/login.ht")
	public ModelAndView login() {
		ModelAndView view = new ModelAndView();
		view.setViewName("login");
		return view;
	}

	@RequestMapping("/main.lg")
	public ModelAndView main(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main");
		StringBuilder builder = new StringBuilder();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				builder.append(cookie.getName() + "|" + cookie.getValue() + "\n");
			}
		}
		modelAndView.addObject("cookie", builder.toString());
		return modelAndView;
	}

	@RequestMapping("/register.th")
	public String register(HttpServletRequest request) {
		String user = request.getParameter("username");
		String pw = request.getParameter("password");
		ResultInfo resultInfo = register(user, pw);
		if (resultInfo.getCode() == 1) {
			return "redirect:login.ht";
		} else {
			return "redirect:register.ht";
		}
	}

	@RequestMapping("/login.th")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String user = request.getParameter("username");
		String pw = request.getParameter("password");
		ResultInfo resultInfo = login(user, pw, response);
		if (resultInfo.getCode() == 1) {
			return "redirect:main.lg";
		} else {
			return "redirect:login.ht";
		}
	}

	@RequestMapping("/register.action")
	@ResponseBody
	public ResultInfo register(@RequestParam(name = "user") String user, @RequestParam(name = "pw") String pw) {
		return userService.register(user, pw);
	}

	@RequestMapping("/login.action")
	@ResponseBody
	public ResultInfo login(@RequestParam(name = "user") String user, @RequestParam(name = "pw") String pw,
			HttpServletResponse response) {
		ResultInfo resultInfo = userService.login(user, pw);
		if (resultInfo.getCode() == 1) {
			User myUser = (User) resultInfo.getData();
			response.addCookie(new Cookie(LoginInterceptor.USER_ID_KEY, "" + myUser.getUserid()));
			String token = UUID.randomUUID().toString() + myUser.getUserid();
			response.addCookie(new Cookie(LoginInterceptor.LOGIN_TOKEN_KEY, token));
			redis.opsForValue().set(myUser.getUserid(), token, Duration.ofDays(30));
		}
		return resultInfo;
	}

	@RequestMapping("/getUser.do")
	@ResponseBody
	public ResultInfo getUser(@RequestParam(name = "userid") String userid) {
		return userService.getUser(userid);
	}

}
