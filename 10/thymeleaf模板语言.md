# thymeleaf模板语言（1）   
本项目参考：   
[1. SpringBoot页面展示Thymeleaf](https://www.jianshu.com/p/a842e5b5012e)https://www.jianshu.com/p/a842e5b5012e       
[2. thymeleaf标签使用详解](https://blog.csdn.net/zht741322694/article/details/80631285)https://blog.csdn.net/zht741322694/article/details/80631285   
以上两个教程写得非常好，可以直接去看这两个教程。     
[本项目地址](https://github.com/xuanu/Hello-Spring-Boot/tree/master/10/Thy1Demo):https://github.com/xuanu/Hello-Spring-Boot/tree/master/10/Thy1Demo
在Spring Boot中，其实是不太推荐使用JSP语言，使用我们来学习一下如何使用thymeleaf模板语言。   
1. 先引入依赖，添加到pom.xml中    
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
```          

2. thymeleaf的一些配置，可省略       
如果不修改模板的路径的话，那么默认路径为：src/main/templates 下。   
以下配置都可以不用配置，直接使用默认的即可。     
#关闭thymeleaf的缓存，不然在开发过程中修改页面不会立刻生效需要重启，生产可配置为true    
```  
spring.thymeleaf.cache=false //推荐关闭缓存    
spring.thymeleaf......可通过自动补全查看属性          
```  

3. 在templates文件夹下创建一个模板页面，以.html结尾。      
thymeleaf的语法都是以：th:开头的    
```
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p th:text = "${username}"></p><br /> 
	<p th:text ="'大家好，我是：'+${username}"></p><br />
</body>
</html>
```      
4. 写一个controller来加载这个页面   
``` 
@RestController
public class ThyController {

	@RequestMapping("/index")
	public ModelAndView testJsp() {
		ModelAndView view = new ModelAndView();
		view.setViewName("index");
		view.addObject("username", "郑治玄");//通过addObject设置属性值。  
		return view;
	}
}
```   

5. 运行程序，看下结果    
访问：`http://localhost:8081/index`      
结果如图：   
![image](https://github.com/xuanu/Hello-Spring-Boot/raw/master/10/img/10_1.PNG)      

