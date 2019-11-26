# Spring Boot使用JDBC连接数据库      
先创建一个表备用   
![image](https://github.com/xuanu/Hello-Spring-Boot/blob/master/3/img/屏幕快照 2019-11-26 12.01.09.png)   

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


