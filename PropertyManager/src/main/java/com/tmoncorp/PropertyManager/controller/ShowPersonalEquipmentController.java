package com.tmoncorp.PropertyManager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.service.MemberService;

/**
 * 
 * @author piro
 *
 */

@Controller
public class ShowPersonalEquipmentController {
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/showMembers", method = RequestMethod.GET)
	public ModelAndView showMembers(HttpServletRequest request) {
		List<String> upperDivisionList = memberService.getUpperDivisions();

		ModelAndView showMembersModelAndView = new ModelAndView();

		showMembersModelAndView.addObject("upperDivisionList", upperDivisionList);
		showMembersModelAndView.setViewName("showMembers");

		showMembersModelAndView = doPagenation(request, showMembersModelAndView, "member");
		return showMembersModelAndView;
	}

	@RequestMapping(value = "/showMembers.tmon/{upperDivision}.tmon", method = RequestMethod.GET)
	public ModelAndView showMembersWithupperDivision(@PathVariable("upperDivision") String upperDivision, HttpServletRequest request) {
		int viewSolePage = 20;
		if (request.getParameter("viewSolePage") != null)
			viewSolePage = Integer.parseInt(request.getParameter("viewSolePage"));

		List<MemberModel> members = memberService.selectMembers(Integer.parseInt(request.getParameter("page")), viewSolePage);
		List<String> upperDivisionList = memberService.getUpperDivisions();
		List<String> lowerDivisionList = memberService.getLowerDivisions(upperDivision);

		ModelAndView showMembersModelAndView = new ModelAndView();
		showMembersModelAndView.addObject("members", members);
		showMembersModelAndView.addObject("upperDivisionList", upperDivisionList);
		showMembersModelAndView.addObject("lowerDivisionList", lowerDivisionList);
		showMembersModelAndView.setViewName("showMembers");
		return showMembersModelAndView;
	}

	@RequestMapping("/retired")
	public ModelAndView showRetired(HttpServletRequest request) {
		ModelAndView showRetiredMembersModelAndView = new ModelAndView();
		showRetiredMembersModelAndView = doPagenation(request, showRetiredMembersModelAndView, "retired");
		showRetiredMembersModelAndView.setViewName("retired");

		return showRetiredMembersModelAndView;
	}

	private ModelAndView doPagenation(HttpServletRequest request, ModelAndView modelAndView, String contentType) {
		int nowPage;
		int startPage;
		int endPage;
		int viewSolePage;
		int maximumPage = 0;

		if (request.getParameter("page") == null)
			nowPage = 1;
		else
			nowPage = Integer.parseInt(request.getParameter("page"));

		HttpSession session = request.getSession();
		
		if (session.getAttribute("viewSolePage") == null)
			viewSolePage = 20;
		else
			viewSolePage = (int) session.getAttribute("viewSolePage");

		if (contentType.compareTo("member") == 0) {
			List<MemberModel> members = memberService.selectMembers(nowPage, viewSolePage);
			maximumPage = memberService.getMaximumPage(viewSolePage);
			modelAndView.addObject("members", members);
		}

		else if (contentType.compareTo("retired") == 0) {
			List<MemberModel> retiredMembers = memberService.getRetiredMembers(nowPage, viewSolePage);
			maximumPage = memberService.getMaximumPageRetired(viewSolePage);
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
