package com.tmoncorp.PropertyManager.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView insertMember(){
		ModelAndView insertMemberModelAndView = new ModelAndView();
		insertMemberModelAndView.setViewName("MemberInsert");
		return insertMemberModelAndView;
	}

	@RequestMapping("/insertingMember")
	public ModelAndView insertion(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		ModelAndView insertingModelAndView = new ModelAndView();
		insertingModelAndView.setViewName("insertingMember");
		int affectedRows = memberService.insertMemberInfomation(request);
		if (affectedRows == 0) {
			insertingModelAndView.addObject("msg", "중복된 사원번호입니다. 다시 입력해주세요!");
			insertingModelAndView.addObject("url", "back");
		}

		else if (affectedRows == 1) {
			insertingModelAndView.addObject("msg", "등록되었습니다!");
			insertingModelAndView.addObject("url", "/memberinsert.tmon");
		}

		return insertingModelAndView;
	}
}
