package cn.zeffect.thy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/error")
public class ErrorController {

	@RequestMapping("/error.ht")
	public ModelAndView error() {
		ModelAndView view = new ModelAndView();
		view.setViewName("error");
		return view;
	}
}
