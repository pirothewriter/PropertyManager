package com.tmoncorp.PropertyManager.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.service.EquipmentService;
import com.tmoncorp.PropertyManager.service.MemberService;
import com.tmoncorp.PropertyManager.service.PagenationService;
import com.tmoncorp.PropertyManager.service.PropertyLogService;

/**
 * 
 * @author pirothewriter
 *
 *         자산의 부여를 관리하는 컨트롤러
 *
 */

@Controller
public class MappingController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private PropertyLogService propertyLogService;

	@Autowired
	private PagenationService pagenation;

	@RequestMapping(value = "/urgentProperty", method = RequestMethod.GET)
	public ModelAndView urgentProperty(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
		ModelAndView urgentingModelAndView = new ModelAndView();

		urgentingModelAndView = pagenation.doEquipmentPagenation(request, urgentingModelAndView, "ownerless");
		urgentingModelAndView.addObject("adAccount", request.getParameter("adAccount"));
		urgentingModelAndView.setViewName("urgentProperty");
		return urgentingModelAndView;
	}

	@RequestMapping(value = "/mapping", method = RequestMethod.POST)
	public @ResponseBody String mapping(HttpServletRequest request) {
		String[] properties = request.getParameterValues("propertyNumber");
		String msg = "SUCCESS";

		for (int index = 0; index < properties.length; index++) {
			propertyLogService.urgentProperty(request.getParameter("adAccount"), properties[index]);
			propertyLogService.urgentPropertyLog(request.getParameter("adAccount"), properties[index]);
		}
		return msg;
	}

	@RequestMapping(value = "/releasing", method = RequestMethod.POST)
	public @ResponseBody String releasing(HttpServletRequest request) {
		String[] properties = request.getParameterValues("propertyNumber");
		String msg = "SUCCESS";
		for (int index = 0; index < properties.length; index++) {
			propertyLogService.releaseProperty(request.getParameter("adAccount"), properties[index]);
			propertyLogService.releasePropertyLog(request.getParameter("adAccount"), properties[index]);
		}
		return msg;
	}

	@RequestMapping("/barcodeInput")
	public ModelAndView barcodeInput(HttpServletRequest request) {
		ModelAndView barcodeModelAndView = new ModelAndView();
		barcodeModelAndView.addObject("adAccount", request.getParameter("adAccount"));
		barcodeModelAndView.setViewName("barcode");
		return barcodeModelAndView;
	}
}
