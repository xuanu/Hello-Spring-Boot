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
