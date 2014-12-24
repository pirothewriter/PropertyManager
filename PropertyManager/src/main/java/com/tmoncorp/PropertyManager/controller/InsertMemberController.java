package com.tmoncorp.PropertyManager.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.service.MemberService;

/**
 * 
 * @author piro
 *
 */

@Controller
public class InsertMemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/memberinsert")
	public ModelAndView insertMember() {
		ModelAndView insertMemberModelAndView = new ModelAndView();
		insertMemberModelAndView.setViewName("MemberInsert");
		return insertMemberModelAndView;
	}

	@RequestMapping(value="/insertingMember", method=RequestMethod.POST)
	public @ResponseBody String insertion(HttpServletRequest request) throws ParseException {
		int affectedRows = memberService.insertMemberInfomation(request);
		String msg = "";
		if (affectedRows == 0) {
			msg = "FAIL";
		}

		else if (affectedRows == 1) {
			msg = "SUCCESS";
		}

		return msg;
	}
}
