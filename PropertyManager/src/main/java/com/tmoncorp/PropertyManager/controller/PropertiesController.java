package com.tmoncorp.PropertyManager.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.service.EquipmentService;
import com.tmoncorp.PropertyManager.service.PagenationService;

/**
 * 
 * @author pirothewriter
 *
 *         전체 자산정보 리스트를 컨트롤
 *
 */

@Controller
public class PropertiesController {
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private PagenationService pagenation;

	@RequestMapping(value = "showAllProperties", method = RequestMethod.GET)
	public ModelAndView showAllProperties(HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView propertiesModelAndView = new ModelAndView();
		propertiesModelAndView = pagenation.doEquipmentPagenation(request, propertiesModelAndView, "all");
		propertiesModelAndView.setViewName("showAllProperties");
		return propertiesModelAndView;
	}
}
