package com.tmoncorp.PropertyManager.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmoncorp.PropertyManager.service.SecurityService;

@Controller
public class SecurityController {
	@Autowired
	private SecurityService securityService;

	@RequestMapping("/login")
	public void login(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Throwable {
		
	}
}
