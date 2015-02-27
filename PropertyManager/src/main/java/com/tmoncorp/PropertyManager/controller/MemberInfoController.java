package com.tmoncorp.PropertyManager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
import com.tmoncorp.PropertyManager.service.SecurityService;

/**
 * 
 * @author piro
 *
 */

@Controller
public class MemberInfoController {
	@Autowired
	private SecurityService securityService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private PropertyLogService propertyLogService;

	@RequestMapping("/memberInfo")
	public ModelAndView showMemberInfo(HttpServletRequest request) {
		ModelAndView memberInfoModelAndView = new ModelAndView();
		MemberModel member = memberService.selectAMember(request.getParameter("adAccount"));
		List<EquipmentModel> properties = equipmentService.selectPropertyOnMember(request.getParameter("adAccount"));

		for (int index = 0; index < properties.size(); index++) {
			properties.get(index).setUrgentDate(propertyLogService.getPropertyUrgentDateNow(properties.get(index).getPropertyNumber(), request.getParameter("adAccount")));
			properties.get(index).setUpperCategory(equipmentService.exchangeCodeToKoreanCategoryName(properties.get(index).getUpperCategory()));
			properties.get(index).setLowerCategory(equipmentService.exchangeCodeToKoreanCategoryName(properties.get(index).getLowerCategory()));
		}

		memberInfoModelAndView.addObject("memberInfo", member);
		memberInfoModelAndView.addObject("propertyInfo", properties);
		memberInfoModelAndView.setViewName("memberInfo");
		return memberInfoModelAndView;
	}

	@RequestMapping("/index")
	public ModelAndView doIndex(HttpServletRequest request) {
		ModelAndView indexModelAndView = new ModelAndView();

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String adAccount = user.getUsername();
		String authority = securityService.getAuthority(adAccount);

		MemberModel loginedUser = memberService.selectAMember(adAccount);
		List<EquipmentModel> properties = equipmentService.selectPropertyOnMember(adAccount);

		for (int index = 0; index < properties.size(); index++) {
			properties.get(index).setUrgentDate(propertyLogService.getPropertyUrgentDateNow(properties.get(index).getPropertyNumber(), adAccount));
			properties.get(index).setUpperCategory(equipmentService.exchangeCodeToKoreanCategoryName(properties.get(index).getUpperCategory()));
			properties.get(index).setLowerCategory(equipmentService.exchangeCodeToKoreanCategoryName(properties.get(index).getLowerCategory()));
		}

		indexModelAndView.addObject("memberInfo", loginedUser);
		indexModelAndView.addObject("propertyInfo", properties);
		indexModelAndView.addObject("authority", authority);

		indexModelAndView.setViewName("myInfo");
		return indexModelAndView;
	}

	@RequestMapping(value = "/getPropertyInfomation", method = RequestMethod.GET)
	public @ResponseBody String getPropertyInfomation(HttpServletRequest request) {
		String result = propertyLogService.getPropertyInfomation(request.getParameter("propertyNumber"));
		return result;
	}

	@RequestMapping(value = "retireMember", method = RequestMethod.GET)
	public @ResponseBody String retireMember(HttpServletRequest request) {
		String result = "";

		int affectedRowsOnMemberTable = memberService.retireMember(request.getParameter("adAccount"));
		int affectedRowsOnMappingTable = propertyLogService.withdrawEqiupmentThatOwnedRetireMember(request.getParameter("adAccount"));
		int affectedRowsOnLogTable = propertyLogService.withdrawEqiupmentThatOwnedRetireMemberLog(request.getParameter("adAccount"));
		int affectedRowsOnSecurityTable = securityService.revokeUser(request.getParameter("adAccount"));

		if (affectedRowsOnMemberTable == 0)
			result = "ERROR";
		else
			result = "SUCCESS";

		return result;
	}

	@RequestMapping(value = "recoverRetirement", method = RequestMethod.GET)
	public @ResponseBody String recoverRetirement(HttpServletRequest request) {
		String result = "";
		int affectedRowsOnMemberTable = memberService.recoverRetirement(request.getParameter("adAccount"));
		int affectedRowOnSecurityTable = securityService.revokeAdmin(request.getParameter("adAccount"));

		if (affectedRowsOnMemberTable == 0 && affectedRowOnSecurityTable == 0)
			result = "ERROR";
		else
			result = "SUCCESS";

		return result;
	}
}
