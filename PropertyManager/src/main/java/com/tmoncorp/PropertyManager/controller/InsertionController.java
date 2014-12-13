package com.tmoncorp.PropertyManager.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.model.EquipmentModel;
import com.tmoncorp.PropertyManager.service.EquipmentService;
import com.tmoncorp.PropertyManager.util.ExchangeDateBetweenString;

@Controller
public class InsertionController {
	@Autowired
	private EquipmentService equipmentService;

	@RequestMapping("/insert")
	public ModelAndView InsertView() {
		ModelAndView indexModelAndView = new ModelAndView();
		indexModelAndView.setViewName("Insert");
		return indexModelAndView;
	}

	@RequestMapping("/inserting")
	public void insertion(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		equipmentService.equipmentInsertion(request);
		
	}
}
