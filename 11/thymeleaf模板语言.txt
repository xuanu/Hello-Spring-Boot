# thymeleaf模板语言（2）   
本项目参考：   
[1. SpringBoot页面展示Thymeleaf](https://www.jianshu.com/p/a842e5b5012e)：https://www.jianshu.com/p/a842e5b5012e       
[2. thymeleaf标签使用详解](https://blog.csdn.net/zht741322694/article/details/80631285)：https://blog.csdn.net/zht741322694/article/details/80631285   
以上两个教程写得非常好，可以直接去看这两个教程。       
我这里只是挑了几个常用的来介绍    
```
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	1. th:text 用来替换标签文本
	<p th:text="${username}"></p>
	<p th:text="'大家好，我是：'+${username}"></p>
	<p th:text="|大家好，我是：${username}|"></p>
	2. th:utext 用来替换带样式的文本
	<p th:utext="${stylename}"></p>
	3. th:href 用来替换网址，规则为@{路径}
	<a th:href="@{/href}">去href页面</a>
	<br /> 4. 我们来传递一个对象过来 (对象必须序列化)，有两种加载方式：1. ${user.name} 2. *{name}
	第一种用法：
	<div>
		<p th:text="|我的名字是：${user.name}|"></p>
		<p th:text="|我${user.age}岁了。|"></p>
	</div>
	第二种用法：
	<div th:object="${user}">
		<p th:text="|我的名字是：*{name}|"></p>
		<p th:text="|我*{age}岁了。|"></p>
	</div>
	<p>测试一下，列表的加载情况->List情况的遍历方式</p>
	<table>
		<tr>
			<td>No.</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>偶数</td>
			<td>奇数</td>
		</tr>
		<tr th:each="user,memberStat:${users}">
			<td th:text="${memberStat.index + 1}" />
			<td th:text="${user.name}" />
			<td th:text="${user.age}" />
			<td th:text="${memberStat.even}" />
			<td th:text="${memberStat.odd}" />
		</tr>
	</table>
	<br />
	<p>测试一下，列表的加载情况->Map情况的遍历方式</p>
	<table>
		<tr>
			<td>No.</td>
			<td>KEY</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>偶数</td>
			<td>奇数</td>
		</tr>
		<tr th:each="memberEntry,memberStat:${usermap}">
			<td th:text="${memberStat.index + 1}" />
			<td th:text="${memberEntry.key}" />
			<td th:text="${memberEntry.value.name}" />
			<td th:text="${memberEntry.value.age}" />
			<td th:text="${memberStat.even}" />
			<td th:text="${memberStat.odd}" />
		</tr>
	</table>
	<br />
</body>
</html>
```      
controller  
```
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
```     
结果如下：   
![image](https://github.com/xuanu/Hello-Spring-Boot/raw/master/11/img/11_1.PNG)      

