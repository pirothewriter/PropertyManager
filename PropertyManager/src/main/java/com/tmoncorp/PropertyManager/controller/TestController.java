package com.tmoncorp.PropertyManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	@RequestMapping("/insert")
	public ModelAndView test() {
		ModelAndView indexModelAndView = new ModelAndView();
		indexModelAndView.setViewName("Insert");
		return indexModelAndView;
	}
}
