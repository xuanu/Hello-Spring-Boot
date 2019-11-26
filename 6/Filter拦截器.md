# Filter拦截器   
[项目地址：](https://github.com/xuanu/Hello-Spring-Boot/tree/master/6/FilterDemo)https://github.com/xuanu/Hello-Spring-Boot/tree/master/6/FilterDemo    

有时候我们的应用中，部分接口的要求用户登录之后方可使用，就可以通过这个Filter来做统一拦截。     
这个其实需要配合Redis来使用，但这里我们写Demo的话，就跳过这个部分，直接模拟一下。    

我将应用中的接口分为两类，一类.action结尾，一类.do结尾，.do结尾需要登录才可访问。    
直接来看拦截器的代码：  
其中@WebFilter中的urlPatterns是匹配哪些链接，即哪些URL需要拦截，可以自己定义。      
然后对于这个do的接口，每一次请求，都需要携带header为login_token的参数，可以通过这个参数去查询redis
```
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

```   
写两个接口来测试一下：   
```
package cn.zeffect.filterdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

	@RequestMapping("/test1.action")
	public String test1() {
		return ".ACTION接口";
	}

	@RequestMapping("/test2.do")
	public String test2() {
		return ".DO接口";
	}
}
```     
在浏览器中访问：  
``` 
http://localhost:8080/test1.action  
http://localhost:8080/test2.do       
```   



