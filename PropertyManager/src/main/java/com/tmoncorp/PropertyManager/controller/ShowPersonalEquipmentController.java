package com.tmoncorp.PropertyManager.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.tmoncorp.PropertyManager.service.PagenationService;
import com.tmoncorp.PropertyManager.util.JsonEncoding;

/**
 * 
 * @author piro
 *
 */

@Controller
public class ShowPersonalEquipmentController {
	private static final String RETIRED = "retired";
	private static final String MEMBER = "member";

	@Autowired
	private MemberService memberService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PagenationService pagenation;

	@RequestMapping(value = "/showMembers", method = RequestMethod.GET)
	public ModelAndView showMembers(HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView showMembersModelAndView = new ModelAndView();
		List<CategoryModel> upperDivisions = categoryService.getAllUpperCategory();

		showMembersModelAndView.addObject("upperDivisions", upperDivisions);
		showMembersModelAndView.setViewName("showMembers");

		showMembersModelAndView = pagenation.doMemberPagenation(request, showMembersModelAndView, MEMBER, memberService);
		return showMembersModelAndView;
	}

	@RequestMapping(value = "/loadLowDivision", method = RequestMethod.POST)
	public @ResponseBody String returningLowerCategories(HttpServletRequest request) {
		JsonEncoding jsonEncoding = new JsonEncoding();
		URLDecoder urlDecoder = new URLDecoder();
		String upperCategoryString = urlDecoder.decode(request.getParameter("upperDivision"));
		int upperDivisionCode = categoryService.selectSpecificCategory(upperCategoryString);
		List<CategoryModel> lowerCategory = categoryService.getLowerCategories(upperDivisionCode);

		return jsonEncoding.encodingJson(lowerCategory);
	}

	@RequestMapping("/retired")
	public ModelAndView showRetired(HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView showRetiredMembersModelAndView = new ModelAndView();

		List<CategoryModel> upperDivisions = categoryService.getAllUpperCategory();
		showRetiredMembersModelAndView.addObject("upperDivisions", upperDivisions);
		showRetiredMembersModelAndView = pagenation.doMemberPagenation(request, showRetiredMembersModelAndView, RETIRED, memberService);
		showRetiredMembersModelAndView.setViewName(RETIRED);

		return showRetiredMembersModelAndView;
	}
}