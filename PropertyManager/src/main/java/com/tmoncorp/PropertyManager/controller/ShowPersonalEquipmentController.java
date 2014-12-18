package com.tmoncorp.PropertyManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView showMembers(){
		List<MemberModel> allMembers = memberService.selectAllMembers();
		
		ModelAndView showMembersModelAndView = new ModelAndView();
		showMembersModelAndView.addObject("members", allMembers);
		showMembersModelAndView.setViewName("showMembers");
		return showMembersModelAndView;
	}
}
