# 使用JSP页面     
[项目地址：](https://github.com/xuanu/Hello-Spring-Boot/tree/master/5/JspDemo)https://github.com/xuanu/Hello-Spring-Boot/tree/master/5/JspDemo
这节我们来简单学习如何添加jsp的支持   
先添加依赖pom.xml    
```
		<!-- servlet依赖 -->
        <dependency>
              <groupId>javax.servlet</groupId>
              <artifactId>javax.servlet-api</artifactId> 
        </dependency>
        <dependency>
              <groupId>javax.servlet</groupId>
              <artifactId>jstl</artifactId>
        </dependency>
        <!-- tomcat的支持-->
        <dependency>
               <groupId>org.apache.tomcat.embed</groupId>
               <artifactId>tomcat-embed-jasper</artifactId>
        </dependency>  
```    
在项目中创建jsp目录，用来存放jsp页面   
`/src/main/webapp/WEB-INF/jsp/`    
在`src/main/resources/application.propreties`添加配置  
```
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
```    
先来创建一个很简单的JSP页面   
`new->other->jsp file `  
``` 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p>我的名字是：${username} </p>
<% for(int i=0;i<100;i++){ %>
<p>郑治玄  </p> <%=i%>
<%} %>
</body>
</html>

```     
jsp页面嵌入代码的用法：   
${变量名} 可以用于获取变量值         
<% 代码块 %>    
<% =变量 %>     

在代码中这样使用：  
```
package cn.zeffect.web.jspdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class JspController {

	@RequestMapping("/index.html")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject("username", "郑治玄");
		return modelAndView;
	}
}
```     

可以访问如下链接 ：  
`http://localhost:8080/index.html `


