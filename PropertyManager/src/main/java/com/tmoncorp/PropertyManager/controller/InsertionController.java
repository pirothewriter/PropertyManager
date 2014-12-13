package com.tmoncorp.PropertyManager.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.service.EquipmentService;

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

	@RequestMapping("/inserting")
	public ModelAndView insertion(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		ModelAndView insertingModelAndView = new ModelAndView();
		insertingModelAndView.setViewName("Inserting");
		int affectedRows = equipmentService.equipmentInsertion(request);
		if(affectedRows == 0){
			insertingModelAndView.addObject("msg", "자산 번호 값이 중복되었습니다! 다시 입력해주세요!");
			insertingModelAndView.addObject("url", "back");
		}
		
		else if(affectedRows == 1){
			insertingModelAndView.addObject("msg", "등록되었습니다!");
			insertingModelAndView.addObject("url", "/insert.tmon");
		}
		
		return insertingModelAndView;
	}
}
