package com.tmoncorp.PropertyManager.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.model.CategoryModel;
import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.service.CategoryService;
import com.tmoncorp.PropertyManager.service.MemberService;
import com.tmoncorp.PropertyManager.util.JsonEncoding;

/**
 * 
 * @author piro
 *
 */

@Controller
public class ShowPersonalEquipmentController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/showMembers", method = RequestMethod.GET)
	public ModelAndView showMembers(HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView showMembersModelAndView = new ModelAndView();

		List<CategoryModel> upperDivisions = categoryService.getAllUpperCategory();

		showMembersModelAndView.addObject("upperDivisions", upperDivisions);
		showMembersModelAndView.setViewName("showMembers");

		showMembersModelAndView = doPagenation(request, showMembersModelAndView, "member");
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
		showRetiredMembersModelAndView = doPagenation(request, showRetiredMembersModelAndView, "retired");
		showRetiredMembersModelAndView.setViewName("retired");

		return showRetiredMembersModelAndView;
	}

	private ModelAndView doPagenation(HttpServletRequest request, ModelAndView modelAndView, String contentType) throws UnsupportedEncodingException {
		int nowPage;
		int startPage;
		int endPage;
		int viewSolePage;
		int maximumPage = 0;
		HttpSession session = request.getSession();

		if (request.getParameter("page") == null)
			nowPage = 1;
		else
			nowPage = Integer.parseInt(request.getParameter("page"));

		if (request.getParameter("viewSolePage") != null)
			session.setAttribute("viewSolePage", request.getParameter("viewSolePage"));

		if (session.getAttribute("viewSolePage") == null)
			viewSolePage = 20;
		else {
			viewSolePage = Integer.parseInt((String) session.getAttribute("viewSolePage"));
		}

		String upperDivision = "";
		String lowerDivision = "";
		String adAccount = "";
		String nameOfMember = "";

		if (contentType.compareTo("member") == 0) {
			if (request.getParameter("upperDivision") != null) {
				if (request.getParameter("upperDivision").compareTo("") != 0)
					upperDivision = URLDecoder.decode(request.getParameter("upperDivision"), "UTF-8");
			}

			if (request.getParameter("lowerDivision") != null) {
				if (request.getParameter("lowerDivision").compareTo("") != 0)
					lowerDivision = URLDecoder.decode(request.getParameter("lowerDivision"), "UTF-8");
			}

			if (request.getParameter("adAccount") != null) {
				if (request.getParameter("adAccount").compareTo("") != 0)
					adAccount = URLDecoder.decode(request.getParameter("adAccount"), "UTF-8");
			}

			if (request.getParameter("nameOfMember") != null) {
				if (request.getParameter("nameOfMember").compareTo("") != 0)
					nameOfMember = URLDecoder.decode(request.getParameter("nameOfMember"), "UTF-8");
			}

			List<MemberModel> members = memberService.selectMembers(nowPage, viewSolePage, upperDivision, lowerDivision, adAccount, nameOfMember);
			maximumPage = memberService.getMaximumPage(viewSolePage, upperDivision, lowerDivision, adAccount, nameOfMember);
			modelAndView.addObject("members", members);
		}

		else if (contentType.compareTo("retired") == 0) {
			if (request.getParameter("upperDivision") != null) {
				if (request.getParameter("upperDivision").compareTo("") != 0)
					upperDivision = URLDecoder.decode(request.getParameter("upperDivision"), "UTF-8");
			}

			if (request.getParameter("lowerDivision") != null) {
				if (request.getParameter("lowerDivision").compareTo("") != 0)
					lowerDivision = URLDecoder.decode(request.getParameter("lowerDivision"), "UTF-8");
			}

			if (request.getParameter("adAccount") != null) {
				if (request.getParameter("adAccount").compareTo("") != 0)
					adAccount = URLDecoder.decode(request.getParameter("adAccount"), "UTF-8");
			}

			if (request.getParameter("nameOfMember") != null) {
				if (request.getParameter("nameOfMember").compareTo("") != 0)
					nameOfMember = URLDecoder.decode(request.getParameter("nameOfMember"), "UTF-8");
			}
			List<MemberModel> retiredMembers = memberService.getRetiredMembers(nowPage, viewSolePage, upperDivision, lowerDivision, adAccount, nameOfMember);
			maximumPage = memberService.getMaximumPageRetired(viewSolePage, upperDivision, lowerDivision, adAccount, nameOfMember);
			modelAndView.addObject("retiredMembers", retiredMembers);
		}

		if (maximumPage > nowPage + 5)
			endPage = nowPage + 5;
		else
			endPage = maximumPage;

		if (nowPage - 5 > 0)
			startPage = nowPage - 5;
		else
			startPage = 1;

		modelAndView.addObject("startPage", startPage);
		modelAndView.addObject("endPage", endPage);
		modelAndView.addObject("viewSolePage", viewSolePage);

		return modelAndView;
	}
}