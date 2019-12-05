package cn.zeffect.thy.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.zeffect.thy.utils.TextUtils;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	public static final String LOGIN_TOKEN_KEY = "login_token";
	public static final String USER_ID_KEY = "userid";
	public static final String LOGIN_OUT_MSG = "{\"code\":-1,\"msg\":\"登录超时\"}";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		if (url.endsWith(".do") || url.endsWith(".lg")) {
			String userid = "";
			String token = "";
			//
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					String key = cookies[i].getName();
					if (key.equals(USER_ID_KEY)) {
						userid = cookies[i].getValue();
						continue;
					}
					if (key.equals(LOGIN_TOKEN_KEY)) {
						token = cookies[i].getValue();
						continue;
					}
				}
			}
			//
			if (TextUtils.isEmpty(userid)) {
				if (url.endsWith(".do")) {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write(LOGIN_OUT_MSG);
					response.getWriter().close();
					return false;
				} else if (url.endsWith(".lg")) {
					response.sendRedirect("/user/login.ht");
					return false;
				}
			}
			if (TextUtils.isEmpty(token)) {
				if (url.endsWith(".do")) {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write(LOGIN_OUT_MSG);
					response.getWriter().close();
					return false;
				} else if (url.endsWith(".lg")) {
					response.sendRedirect("/user/login.ht");
					return false;
				}
			}
			return true;
		} else {
			return true;
		}
	}
}
