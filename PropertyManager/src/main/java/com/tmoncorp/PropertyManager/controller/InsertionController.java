package com.tmoncorp.PropertyManager.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.service.EquipmentService;

/**
 * 
 * @author piro
 *
 */

@Controller
public class InsertionController {
	@Autowired
	private EquipmentService equipmentService;

	@RequestMapping("/insert")
	public ModelAndView InsertView() {
		ModelAndView insertModelAndView = new ModelAndView();
		insertModelAndView.setViewName("Insert");
		return insertModelAndView;
	}

	@RequestMapping(value = "/inserting", method = RequestMethod.POST)
	public @ResponseBody String insertion(HttpServletRequest request) throws ParseException {
		String msg = "";
		int affectedRows = equipmentService.equipmentInsertion(request);
		if (affectedRows == 0) {
			msg = "FAIL";
		}

		else if (affectedRows == 1) {
			msg = "SUCCESS";
		}

		return msg;
	}

}
