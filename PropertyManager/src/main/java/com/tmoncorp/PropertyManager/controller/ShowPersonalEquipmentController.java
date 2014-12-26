package com.tmoncorp.PropertyManager.controller;

import java.util.List;

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

	@RequestMapping("/showMembers")
	public ModelAndView showMembers() {
		List<MemberModel> members = memberService.selectMembers();
		List<String> upperDivisionList = memberService.getUpperDivisions();

		ModelAndView showMembersModelAndView = new ModelAndView();
		showMembersModelAndView.addObject("members", members);
		showMembersModelAndView.addObject("upperDivisionList", upperDivisionList);
		showMembersModelAndView.setViewName("showMembers");
		return showMembersModelAndView;
	}

	@RequestMapping(value = "/showMembers.tmon/{upperDivision}.tmon", method = RequestMethod.GET)
	public ModelAndView showMembersWithupperDivision(@PathVariable("upperDivision") String upperDivision) {
		List<MemberModel> members = memberService.selectMembers();
		List<String> upperDivisionList = memberService.getUpperDivisions();
		List<String> lowerDivisionList = memberService.getLowerDivisions(upperDivision);

		ModelAndView showMembersModelAndView = new ModelAndView();
		showMembersModelAndView.addObject("members", members);
		showMembersModelAndView.addObject("upperDivisionList", upperDivisionList);
		showMembersModelAndView.addObject("lowerDivisionList", lowerDivisionList);
		showMembersModelAndView.setViewName("showMembers");
		return showMembersModelAndView;
	}

}
