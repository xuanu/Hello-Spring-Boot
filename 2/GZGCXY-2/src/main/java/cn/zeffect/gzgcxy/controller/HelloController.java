package cn.zeffect.gzgcxy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 所有的接口类，都需要通过@RestController注解
@RequestMapping("/hello/") // 可以给类添加注解@RequestMapping，这样，本类下的所有接口的请求地址，能要添加这个前缀
public class HelloController {

	@RequestMapping("/hello.action") // 想要被外界访问的接口，都需要设置RequestionMapping
	public String hello() {
		return "Hello Spring Boot !";
	}

	@RequestMapping("/say.action")
	public String say(@RequestParam(name = "talk") String talk) {//可以通过@RequestParam来指定参数的名字
		return "你说的是：" + talk;
	}
	
	@RequestMapping(value = "/sum.action",method = RequestMethod.GET) //可以通过method指定请求的方式，如GET和POST
	public int sum(
			@RequestParam(name = "a",defaultValue = "1")int a1,
			@RequestParam(name = "b",required = true)int b1) {//可以指定参数的默认值，也可以指定参数是否必传。如果有默认值，则认为可以不传。
		return a1+b1;
	}

}
