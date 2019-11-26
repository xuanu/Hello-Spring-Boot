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
