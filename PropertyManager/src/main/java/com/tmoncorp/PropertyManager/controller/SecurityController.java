package com.tmoncorp.PropertyManager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.service.MemberService;
import com.tmoncorp.PropertyManager.service.SecurityService;

@Controller
public class SecurityController {
	@Autowired
	private SecurityService securityService;

	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/login")
	public void login(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Throwable {

	}

	@RequestMapping("/loginFail")
	public ModelAndView loginFail() {
		ModelAndView loginFailModelAndView = new ModelAndView();
		loginFailModelAndView.setViewName("loginFail");
		return loginFailModelAndView;
	}

	@RequestMapping("/adminList")
	public ModelAndView administratorList() {
		ModelAndView grantModelAndView = new ModelAndView();
		List<MemberModel> admins = memberService.getAdmins();
		grantModelAndView.addObject("members", admins);
		grantModelAndView.setViewName("adminList");
		return grantModelAndView;
	}

	@RequestMapping(value = "grantAdmin", method = RequestMethod.POST)
	public @ResponseBody String grantAdmin(HttpServletRequest request) {
		int affectedRow = securityService.grantAdmin(request.getParameter("adAccount"));

		if (affectedRow == 1)
			return "SUCCESS";
		else
			return "ERROR";
	}

	@RequestMapping(value = "revokeAdmin", method = RequestMethod.POST)
	public @ResponseBody String revokeAdmin(HttpServletRequest request) {
		int affectedRow = securityService.revokeAdmin(request.getParameter("adAccount"));

		if (affectedRow == 1)
			return "SUCCESS";
		else
			return "ERROR";
	}

	@RequestMapping("/changePassword")
	public ModelAndView changePassword() {
		ModelAndView changePasswordModelAndView = new ModelAndView();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String adAccount = user.getUsername();
		String password = user.getPassword();
		
		changePasswordModelAndView.addObject("adAccount", adAccount);
		changePasswordModelAndView.addObject("password", password);
		changePasswordModelAndView.setViewName("changePw");
		return changePasswordModelAndView;
	}

	@RequestMapping(value = "/changingPassword", method = RequestMethod.POST)
	public @ResponseBody String changingPassword(HttpServletRequest request) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String adAccount = user.getUsername();
		String password = request.getParameter("toBePassword");
		int result = securityService.changePassword(adAccount, password);

		if (result == 0)
			return "ERROR";
		else
			return "SUCCESS";
	}
}
