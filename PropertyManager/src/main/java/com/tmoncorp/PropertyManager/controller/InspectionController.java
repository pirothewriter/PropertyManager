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
import com.tmoncorp.PropertyManager.model.InspectionNthModel;
import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.service.CategoryService;
import com.tmoncorp.PropertyManager.service.InspectionService;
import com.tmoncorp.PropertyManager.service.MemberService;
import com.tmoncorp.PropertyManager.service.PagenationService;

/**
 * 
 * @author pirothewriter
 *
 */

@Controller
public class InspectionController {
	@Autowired
	private InspectionService inspectionService;

	@Autowired
	private PagenationService pagenationService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/inspection", method = RequestMethod.GET)
	public ModelAndView inspectionIndex(HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView inspectionModelAndView = new ModelAndView();
		List<CategoryModel> upperDivisions = categoryService.getAllUpperCategory();
		List<InspectionNthModel> nthList = inspectionService.getNthList();
		int lastestNth = inspectionService.getLastestNth();
		
		if(request.getParameter("flagDifference") != null){
			inspectionModelAndView.addObject("flagDifference", request.getParameter("flagDifference"));
		}

		inspectionModelAndView.addObject("upperDivisions", upperDivisions);
		inspectionModelAndView.addObject("nthList", nthList);
		inspectionModelAndView.addObject("lastestNth", lastestNth);
		inspectionModelAndView = pagenationService.doInspectionPagenation(request, inspectionModelAndView, inspectionService);
		inspectionModelAndView.setViewName("inspection");
		return inspectionModelAndView;
	}

	@RequestMapping(value = "/inspectionInsert")
	public ModelAndView inspectionInsertion(HttpServletRequest request) {
		ModelAndView inspectionInsertModelAndView = new ModelAndView();
		int lastestNth = inspectionService.getLastestNth();
		inspectionInsertModelAndView.addObject("nth", lastestNth);
		inspectionInsertModelAndView.setViewName("inspectionInsert");
		return inspectionInsertModelAndView;
	}

	@RequestMapping(value = "/getPropertyInfomationForInspection", method = RequestMethod.GET)
	public @ResponseBody String getPropertyInfomation(HttpServletRequest request) {
		String result = inspectionService.getPropertyInfomation(Integer.parseInt(request.getParameter("nth")), request.getParameter("propertyNumber"));
		return result;
	}

	@RequestMapping(value = "/endNth", method = RequestMethod.GET)
	public @ResponseBody String endNth(HttpServletRequest request) {
		int result = inspectionService.endNth(Integer.parseInt(request.getParameter("nth")));
		if (result > 0)
			return "SUCCESS";
		else
			return "ERROR";
	}

	@RequestMapping(value = "/getMemberByMemberName", method = RequestMethod.GET)
	public ModelAndView getMemberByMemberName(HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView chooseMemberModelAndView = new ModelAndView();
		List<MemberModel> members = memberService.getMemberByMemberName(URLDecoder.decode(request.getParameter("memberName"), "UTF-8"));

		chooseMemberModelAndView.setViewName("chooseMember");
		chooseMemberModelAndView.addObject("members", members);

		return chooseMemberModelAndView;
	}

	@RequestMapping(value = "/saveInspectedData", method = RequestMethod.POST)
	public @ResponseBody String insertInspectedData(HttpServletRequest request) {
		int result = inspectionService.insertInspectedData(request);

		if (result > 0)
			return "SUCCESS";
		else
			return "ERROR";
	}
}
