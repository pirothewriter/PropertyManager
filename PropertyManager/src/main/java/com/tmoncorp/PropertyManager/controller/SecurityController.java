package com.tmoncorp.PropertyManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {
	@RequestMapping("/login")
	public ModelAndView loginPage(){
		ModelAndView loginModelAndView = new ModelAndView();
		loginModelAndView.setViewName("login");
		return loginModelAndView;
	}
}
