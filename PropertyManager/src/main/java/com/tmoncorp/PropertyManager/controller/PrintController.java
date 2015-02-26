package com.tmoncorp.PropertyManager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.model.EquipmentModel;
import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.service.EquipmentService;
import com.tmoncorp.PropertyManager.service.MemberService;
import com.tmoncorp.PropertyManager.service.PropertyLogService;
import com.tmoncorp.PropertyManager.util.ExchangeDateBetweenString;

@Controller
public class PrintController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private PropertyLogService propertyLogService;

	@RequestMapping(value = "/printUrgentingProperties", method = RequestMethod.GET)
	public ModelAndView printUrgentProperty(HttpServletRequest request) {
		ModelAndView printModelAndView = new ModelAndView();
		MemberModel memberInfo = memberService.selectAMember(request.getParameter("adAccount"));
		List<EquipmentModel> propertiesOnMember = equipmentService.selectPropertyOnMember(request.getParameter("adAccount"));

		for (int index = 0; index < propertiesOnMember.size(); index++)
			propertiesOnMember.get(index).setUrgentDate(propertyLogService.getPropertyUrgentDateNow(propertiesOnMember.get(index).getPropertyNumber(), request.getParameter("adAccount")));
		
		ExchangeDateBetweenString exchangeDateBetweenString = new ExchangeDateBetweenString();
		String nowDate = exchangeDateBetweenString.returnNowDate();

		printModelAndView.addObject("memberInfo", memberInfo);
		printModelAndView.addObject("properties", propertiesOnMember);
		printModelAndView.addObject("numberOfProperties", propertiesOnMember.size());
		printModelAndView.addObject("nowDate", nowDate);

		printModelAndView.setViewName("propertyUrgentForm");
		return printModelAndView;
	}
}
