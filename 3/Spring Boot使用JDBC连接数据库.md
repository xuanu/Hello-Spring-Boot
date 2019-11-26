# Spring Boot使用JDBC连接数据库      
先创建一个表备用   
![image](https://github.com/xuanu/Hello-Spring-Boot/blob/master/3/img/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-11-26%2012.01.09.png)   

在项目根下的pom.xml中添加依赖   
```
		<!--JDBC -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
```  
在src/main/resources/application.properties中添加配置      
```
spring.datasource.url=jdbc:mysql://192.168.26.41:3306/gzgcxy?serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```     

接下来就可以在代码中使用JDBC连接数据库了,
先看一下接口的写法，写了两个接口，一个用于获取某个用户信息，一个用于获取全部用户信息       
```
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

```    
再看一下UserDao的写法    
User是一个对象，就不列出结果了。   
```
package cn.zeffect.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.zeffect.jdbc.model.User;

@Repository // Dao需要使用@Repository注解
public class UserDao {
	@Resource // 可以通过Resource注解，也可以通过@Autowired注解
	private JdbcTemplate jdbc;

	/**
	 * 根据Userid查询某人信息
	 * 
	 * @param userid
	 * @return
	 */
	public User getUser(String userid) {
		String sql = "select * from user where userid = ?";
		try {// 当没有记录时，会报错，所以通过try catch来拦截
			return jdbc.queryForObject(sql, new Object[] { userid }, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User tmpUser = new User();
					tmpUser.setUser(rs.getString("user"));// 这里ResultSet里的user是表字段的名字。
					tmpUser.setUserid(rs.getString("userid"));// userid也是
					return tmpUser;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}

	public List<User> getUsers() {
		String sql = "select * from user";
		return jdbc.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User tmpUser = new User();
				tmpUser.setUser(rs.getString("user"));// 这里ResultSet里的user是表字段的名字。
				tmpUser.setUserid(rs.getString("userid"));// userid也是
				return tmpUser;
			}
		});
	}

}

```     
可以访问如下地址查看结果 ：  
```
http://localhost:8080/user/getUsers   
http://localhost:8080/user/getUser?userid=d96753fc-5284-4bdf-82b2-acf4d4567438
```  

