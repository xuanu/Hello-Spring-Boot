package cn.zeffect.filterdemo.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/**", filterName = "login_filter")
public class LoginFilter implements Filter {

	public static final String LOGIN_OUT_MSG = "{\"code\":-1,\"msg\":\"登录超时\"}";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURI();
		if (url.endsWith(".do")) {// 如果是.do结尾的接口，则认为是重要接口，需要登录之后访问。
			boolean isLogin = new Random().nextBoolean();// 随机一个结果,这个可以结合Redis来做登录超时的工作。
			if (!isLogin) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(LOGIN_OUT_MSG);
				out.close();
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

}
