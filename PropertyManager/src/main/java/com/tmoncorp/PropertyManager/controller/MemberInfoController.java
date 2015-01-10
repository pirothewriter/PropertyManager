package com.tmoncorp.PropertyManager.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.model.EquipmentModel;
import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.service.EquipmentService;
import com.tmoncorp.PropertyManager.service.MemberService;
import com.tmoncorp.PropertyManager.service.PropertyLogService;

/**
 * 
 * @author piro
 *
 */

@Controller
public class MemberInfoController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private PropertyLogService propertyLogService;

	@RequestMapping("/memberInfo")
	public ModelAndView showMemberInfo(HttpServletRequest request) {
		ModelAndView memberInfoModelAndView = new ModelAndView();
		MemberModel member = memberService.selectAMember(request.getParameter("memberId"));
		List<EquipmentModel> properties = equipmentService.selectPropertyOnMember(request.getParameter("memberId"));

		for (int index = 0; index < properties.size(); index++) {
			properties.get(index).setUrgentDate(propertyLogService.getPropertyUrgentDateNow(properties.get(index).getPropertyNumber(), request.getParameter("memberId")));
		}

		memberInfoModelAndView.addObject("memberInfo", member);
		memberInfoModelAndView.addObject("propertyInfo", properties);
		memberInfoModelAndView.setViewName("memberInfo");
		return memberInfoModelAndView;
	}

	@RequestMapping(value = "/urgentProperty", method= RequestMethod.GET)
	public ModelAndView urgentProperty(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
		ModelAndView urgentingModelAndView = new ModelAndView();
		urgentingModelAndView = doPagenation(request, urgentingModelAndView, "property");
		urgentingModelAndView.addObject("memberId", request.getParameter("memberId"));
		urgentingModelAndView.setViewName("urgentProperty");
		return urgentingModelAndView;
	}

	@RequestMapping(value = "/mapping", method = RequestMethod.POST)
	public @ResponseBody String mapping(HttpServletRequest request) {
		String[] properties = request.getParameterValues("propertyNumber");
		String msg = "SUCCESS";
		
		for(int index = 0; index < properties.length; index++){
			propertyLogService.urgentProperty(request.getParameter("memberId"), properties[index]);
			propertyLogService.urgentPropertyLog(request.getParameter("memberId"), properties[index]);
		}
		return msg;
	}

	@RequestMapping(value = "/releasing", method = RequestMethod.POST)
	public @ResponseBody String releasing(HttpServletRequest request) {
		String[] properties = request.getParameterValues("propertyNumber");
		String msg = "SUCCESS";
		for (int index = 0; index < properties.length; index++) {
			propertyLogService.releaseProperty(request.getParameter("memberId"), properties[index]);
			propertyLogService.releasePropertyLog(request.getParameter("memberId"), properties[index]);
		}
		return msg;
	}
	
	@RequestMapping(value = "retireMember", method=RequestMethod.GET)
	public @ResponseBody String retireMember(HttpServletRequest request){
		String result = "";
		
		int affectedRowsOnMemberTable = memberService.retireMember(request.getParameter("memberId"));
		int affectedRowsOnMappingTable = propertyLogService.withdrawEqiupmentThatOwnedRetireMember(request.getParameter("memberId"));
		int affectedRowsOnLogTable = propertyLogService.withdrawEqiupmentThatOwnedRetireMemberLog(request.getParameter("memberId"));
		
		if(affectedRowsOnLogTable * affectedRowsOnMappingTable * affectedRowsOnMemberTable == 0)
			result = "ERROR";
		else
			result = "SUCCESS";
		
		return result;
	}
	
	private ModelAndView doPagenation(HttpServletRequest request, ModelAndView modelAndView, String contentType) throws UnsupportedEncodingException, ParseException {
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
			session.setAttribute("viewSolePage",  request.getParameter("viewSolePage"));
		
		if (session.getAttribute("viewSolePage") == null)
			viewSolePage = 20;
		else {
			viewSolePage = Integer.parseInt((String)session.getAttribute("viewSolePage"));
		}

		if (maximumPage > nowPage + 5)
			endPage = nowPage + 5;
		else
			endPage = maximumPage;

		if (nowPage - 5 > 0)
			startPage = nowPage - 5;
		else
			startPage = 1;
		
		String upperCategory = "";
		String lowerCategory = "";

		if (request.getParameter("upperCategory") != null) {
			if (request.getParameter("upperCategory").compareTo("") != 0)
				upperCategory = URLDecoder.decode(request.getParameter("upperCategory"), "UTF-8");
		}

		if (request.getParameter("lowerCategory") != null) {
			if (request.getParameter("lowerCategory").compareTo("") != 0)
				lowerCategory = URLDecoder.decode(request.getParameter("lowerCategory"), "UTF-8");
		}

		List<EquipmentModel> ownerlessEquipment = equipmentService.getOwnerlessEquipment(nowPage, viewSolePage, upperCategory, lowerCategory);
		maximumPage = equipmentService.getMaximumPage(viewSolePage);
		
		modelAndView.addObject("startPage", startPage);
		modelAndView.addObject("endPage", endPage);
		modelAndView.addObject("viewSolePage", viewSolePage);
		modelAndView.addObject("ownerlessEquipment", ownerlessEquipment);

		return modelAndView;
	}
}
