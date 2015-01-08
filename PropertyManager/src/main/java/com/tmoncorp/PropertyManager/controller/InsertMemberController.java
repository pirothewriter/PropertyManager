package com.tmoncorp.PropertyManager.controller;

import java.net.URLDecoder;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.model.CategoryModel;
import com.tmoncorp.PropertyManager.service.CategoryService;
import com.tmoncorp.PropertyManager.service.MemberService;
import com.tmoncorp.PropertyManager.util.JsonEncoding;

/**
 * 
 * @author piro
 *
 */

@Controller
public class InsertMemberController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/memberinsert")
	public ModelAndView insertMember() {
		ModelAndView insertMemberModelAndView = new ModelAndView();
		List<CategoryModel> upperCategory = categoryService.getAllUpperCategory();
		insertMemberModelAndView.addObject("upperCategory", upperCategory);

		insertMemberModelAndView.setViewName("MemberInsert");
		return insertMemberModelAndView;
	}

	@RequestMapping(value = "/loadLowerDivision", method = RequestMethod.POST)
	public @ResponseBody String returningLowerCategories(HttpServletRequest request) {
		JsonEncoding jsonEncoding = new JsonEncoding();
		URLDecoder urlDecoder = new URLDecoder();
		String upperCategoryString = urlDecoder.decode(request.getParameter("upperDivision"));
		int upperDivisionCode = categoryService.selectSpecificCategory(upperCategoryString);
		List<CategoryModel> lowerCategory = categoryService.getLowerCategories(upperDivisionCode);

		return jsonEncoding.encodingJson(lowerCategory);
	}

	@RequestMapping("/modifyMember")
	public ModelAndView modifyMember(HttpServletRequest request) {
		ModelAndView modifyMemberModelAndView = new ModelAndView();

		List<CategoryModel> upperCategory = categoryService.getAllUpperCategory();

		modifyMemberModelAndView.addObject("upperCategory", upperCategory);

		modifyMemberModelAndView.addObject("member", memberService.selectAMember(request.getParameter("memberId")));
		modifyMemberModelAndView.setViewName("modifyMember");
		return modifyMemberModelAndView;
	}

	@RequestMapping(value = "/insertingMember", method = RequestMethod.POST)
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

	@RequestMapping(value = "/modifyingMember", method = RequestMethod.POST)
	public @ResponseBody String modifying(HttpServletRequest request) {
		int affectedRows = memberService.modifyMemberInformation(request);
		String msg = "";

		if (affectedRows == 1) {
			msg = "SUCCESS";
		}

		else {
			msg = "ERROR";
		}

		return msg;
	}
}
