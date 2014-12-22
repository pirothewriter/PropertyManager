package com.tmoncorp.PropertyManager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/urgentProperty")
	public ModelAndView urgentProperty(HttpServletRequest request) {
		ModelAndView urgentingModelAndView = new ModelAndView();
		List<EquipmentModel> ownerlessEquipments = equipmentService.getOwnerlessEquipment();
		urgentingModelAndView.addObject("ownerlessEquipment", ownerlessEquipments);
		urgentingModelAndView.addObject("memberId", request.getParameter("memberId"));
		urgentingModelAndView.setViewName("urgentProperty");
		return urgentingModelAndView;
	}
	
	@RequestMapping("/mapping")
	public ModelAndView mapping(HttpServletRequest request){
		ModelAndView mappingModelAndView = new ModelAndView();
		propertyLogService.urgentProperty(request.getParameter("memberId"), request.getParameter("check_property"));
		propertyLogService.urgentPropertyLog(request.getParameter("memberId"), request.getParameter("check_property"));
		mappingModelAndView.addObject("msg", "등록되었습니다!");
		mappingModelAndView.addObject("url", "/memberInfo.tmon?memberId=" + request.getParameter("memberId"));
		mappingModelAndView.setViewName("mapping");
		return mappingModelAndView;
	}
}
