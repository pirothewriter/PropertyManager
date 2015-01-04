package com.tmoncorp.PropertyManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmoncorp.PropertyManager.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	
}
