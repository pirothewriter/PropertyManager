package com.tmoncorp.PropertyManager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.model.PropertyLogModel;
import com.tmoncorp.PropertyManager.service.PropertyLogService;

/**
 * 
 * @author piro
 *
 */

@Controller
public class PersonalEquipmentLogController {
	@Autowired
	private PropertyLogService propertyLogService;
	
	@RequestMapping(value="personalLog", method=RequestMethod.GET)
	public ModelAndView showPersonalLog(HttpServletRequest request){
		ModelAndView personalLogModelAndView = new ModelAndView();
		List<PropertyLogModel> personalLog = propertyLogService.getPersonalLog(request.getParameter("memberId"));
		personalLogModelAndView.addObject("logs", personalLog);
		personalLogModelAndView.setViewName("personalEquipmentLog");
		return personalLogModelAndView;
	}
	
	@RequestMapping("/equipmentLog")
	public ModelAndView viewLog(HttpServletRequest request) {
		ModelAndView logModelAndView = new ModelAndView();
		List<PropertyLogModel> equipmentLog = propertyLogService.getEquipmentLog(request.getParameter("propertyNumber"));
		logModelAndView.addObject("equipmentLog", equipmentLog);
		logModelAndView.setViewName("equipmentLog");
		return logModelAndView;
	}
}
