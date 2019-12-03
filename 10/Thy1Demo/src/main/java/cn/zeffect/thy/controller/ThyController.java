package cn.zeffect.thy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
