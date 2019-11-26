# Redis简单用法       

redis的安装及在SpringBoot中的使用，可参考：[【SpringBoot系列】七、SpringBoot 中使用Redis缓存](https://blog.csdn.net/xcbeyond/article/details/81116600)    
[本项目地址](https://github.com/xuanu/Hello-Spring-Boot/tree/master/4/RedisDemo)：https://github.com/xuanu/Hello-Spring-Boot/tree/master/4/RedisDemo):

Redis是服务器的一个缓存工具，可用作缓存使用    
假设要做一个登录超时的功能，你会怎么做？   
AB两台机子：   
A机器登录账号1，新建Cookie值存放到Redis中            
B机器登录账号1，新建Cookie值存放到Redis中       
只会有一个值存放到Redis中，那么如果，在访问其它接口时，我们判断一下Cookie和Redis里的Cookie值是否一致，就可以判断是否登录超时。    

添加依赖：在pom.xml中添加   
```
		<!-- redis依赖包 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
```   

在`src/main/resources/application.propreties`中添加配置  ，根据自己的Redis修改：  
``` 
spring.redis.host=192.168.26.41
spring.redis.port=6379
spring.redis.password=123456
```     
编写测试代码：
```
package cn.zeffect.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/")
public class RedisController {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@RequestMapping("/add_redis.action")
	public String addKey(@RequestParam(name = "key") String key, @RequestParam(name = "value") String value) {
		redisTemplate.opsForValue().set(key, value);
		return "成功";
	}

	@RequestMapping("/get_redis.action")
	public String getRedis(@RequestParam(name = "key") String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@RequestMapping("/del_redis.action")
	public String delRedis(@RequestParam(name = "key") String key) {
		return "删除：" + redisTemplate.delete(key);
	}

}

```   

