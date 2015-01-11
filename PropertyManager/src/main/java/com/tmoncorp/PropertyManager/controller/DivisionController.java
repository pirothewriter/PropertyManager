package com.tmoncorp.PropertyManager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.model.CategoryModel;
import com.tmoncorp.PropertyManager.service.CategoryService;

@Controller
public class DivisionController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "showDivisions", method = RequestMethod.GET)
	public ModelAndView showDivisions(HttpServletRequest request) {
		ModelAndView divisionModelAndView = new ModelAndView();
		List<CategoryModel> upperDivisions = categoryService.getAllUpperCategory();
		List<CategoryModel> allLowerDivisions = categoryService.getAllLowerCategory();

		for (int index = 0; index < upperDivisions.size(); index++) {
			int divisionCode = upperDivisions.get(index).getCategoryCode();
			upperDivisions.get(index).setLowerCategories(categoryService.selectUpperCategoryCount(divisionCode));
		}

		divisionModelAndView.addObject("upperDivisions", upperDivisions);
		divisionModelAndView.addObject("lowerDivisions", allLowerDivisions);
		divisionModelAndView.setViewName("showDivisions");
		
		return divisionModelAndView;
	}
}
