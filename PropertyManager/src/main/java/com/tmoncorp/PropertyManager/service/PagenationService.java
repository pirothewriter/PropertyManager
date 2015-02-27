package com.tmoncorp.PropertyManager.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.tmoncorp.PropertyManager.model.EquipmentModel;
import com.tmoncorp.PropertyManager.model.MemberModel;

@Service
public class PagenationService {
	private static final int DEFAULT_SOLE_PAGE = 20;

	@Autowired
	private MemberService memberService;

	@Autowired
	private EquipmentService equipmentService;

	public ModelAndView doMemberPagenation(HttpServletRequest request, ModelAndView modelAndView, String contentType) throws UnsupportedEncodingException {
		int nowPage;
		int startPage;
		int endPage;
		int viewSolePage;
		int maximumPage = 0;

		String upperDivision = "";
		String lowerDivision = "";
		String adAccount = "";
		String nameOfMember = "";

		HttpSession session = request.getSession();

		if (request.getParameter("page") == null)
			nowPage = 1;
		else
			nowPage = Integer.parseInt(request.getParameter("page"));

		if (request.getParameter("viewSolePage") != null)
			session.setAttribute("viewSolePage", request.getParameter("viewSolePage"));

		if (session.getAttribute("viewSolePage") == null || session.getAttribute("viewSolePage") == "")
			viewSolePage = DEFAULT_SOLE_PAGE;
		else
			viewSolePage = Integer.parseInt((String) session.getAttribute("viewSolePage"));

		if (request.getParameter("upperDivision") != null) {
			if (request.getParameter("upperDivision").compareTo("") != 0)
				upperDivision = URLDecoder.decode(request.getParameter("upperDivision"), "UTF-8");
		}

		if (request.getParameter("lowerDivision") != null) {
			if (request.getParameter("lowerDivision").compareTo("") != 0)
				lowerDivision = URLDecoder.decode(request.getParameter("lowerDivision"), "UTF-8");
		}

		if (request.getParameter("adAccount") != null) {
			if (request.getParameter("adAccount").compareTo("") != 0)
				adAccount = URLDecoder.decode(request.getParameter("adAccount"), "UTF-8");
		}

		if (request.getParameter("nameOfMember") != null) {
			if (request.getParameter("nameOfMember").compareTo("") != 0)
				nameOfMember = URLDecoder.decode(request.getParameter("nameOfMember"), "UTF-8");
		}

		if (contentType.compareTo("member") == 0) {
			List<MemberModel> members = memberService.selectMembers(nowPage, viewSolePage, upperDivision, lowerDivision, adAccount, nameOfMember);
			maximumPage = memberService.getMaximumPage(viewSolePage, upperDivision, lowerDivision, adAccount, nameOfMember);
			modelAndView.addObject("members", members);
		}

		else if (contentType.compareTo("retired") == 0) {
			List<MemberModel> retiredMembers = memberService.getRetiredMembers(nowPage, viewSolePage, upperDivision, lowerDivision, adAccount, nameOfMember);
			maximumPage = memberService.getMaximumPageRetired(viewSolePage, upperDivision, lowerDivision, adAccount, nameOfMember);
			modelAndView.addObject("retiredMembers", retiredMembers);
		}

		if (maximumPage > nowPage + 5)
			endPage = nowPage + 5;
		else
			endPage = maximumPage;

		if (nowPage - 5 > 0)
			startPage = nowPage - 5;
		else
			startPage = 1;

		modelAndView.addObject("startPage", startPage);
		modelAndView.addObject("endPage", endPage);
		modelAndView.addObject("viewSolePage", viewSolePage);

		return modelAndView;
	}

	public ModelAndView doEquipmentPagenation(HttpServletRequest request, ModelAndView modelAndView, String contentType) throws UnsupportedEncodingException {
		int nowPage;
		int startPage;
		int endPage;
		int viewSolePage;
		int maximumPage = 0;

		String upperCategory = "";
		String lowerCategory = "";
		String propertyNumber = "";

		HttpSession session = request.getSession();

		if (request.getParameter("page") == null)
			nowPage = 1;
		else
			nowPage = Integer.parseInt(request.getParameter("page"));

		if (request.getParameter("viewSolePage") != null)
			session.setAttribute("viewSolePage", request.getParameter("viewSolePage"));

		if (session.getAttribute("viewSolePage") == null)
			viewSolePage = DEFAULT_SOLE_PAGE;
		else
			viewSolePage = Integer.parseInt((String) session.getAttribute("viewSolePage"));

		if (request.getParameter("upperCategory") != null) {
			if (request.getParameter("upperCategory").compareTo("") != 0)
				upperCategory = URLDecoder.decode(request.getParameter("upperCategory"), "UTF-8");
		}

		if (request.getParameter("lowerCategory") != null) {
			if (request.getParameter("lowerCategory").compareTo("") != 0)
				lowerCategory = URLDecoder.decode(request.getParameter("lowerCategory"), "UTF-8");
		}

		if (request.getParameter("propertyNumber") != null) {
			if (request.getParameter("propertyNumber").compareTo("") != 0)
				propertyNumber = URLDecoder.decode(request.getParameter("propertyNumber"), "UTF-8");
		}

		if (contentType.compareTo("ownerless") == 0) {
			List<EquipmentModel> ownerlessEquipment = equipmentService.getOwnerlessEquipment(nowPage, viewSolePage, upperCategory, lowerCategory, propertyNumber);
			maximumPage = equipmentService.getMaximumPageOfOwnerless(viewSolePage, upperCategory, lowerCategory, propertyNumber);
			ownerlessEquipment = exchangeCodeToCategoryName(ownerlessEquipment);
			
			modelAndView.addObject("ownerlessEquipment", ownerlessEquipment);
		}

		else if (contentType.compareTo("all") == 0) {
			List<EquipmentModel> properties = equipmentService.getAllEquipment(nowPage, viewSolePage, upperCategory, lowerCategory, propertyNumber);
			maximumPage = equipmentService.getMaximumPage(viewSolePage, upperCategory, lowerCategory, propertyNumber);
			properties = exchangeCodeToCategoryName(properties);
			
			modelAndView.addObject("properties", properties);
		}

		if (maximumPage > nowPage + 5)
			endPage = nowPage + 5;
		else
			endPage = maximumPage;

		if (nowPage - 5 > 0)
			startPage = nowPage - 5;
		else
			startPage = 1;

		modelAndView.addObject("startPage", startPage);
		modelAndView.addObject("endPage", endPage);
		modelAndView.addObject("viewSolePage", viewSolePage);

		return modelAndView;
	}

	private List<EquipmentModel> exchangeCodeToCategoryName(List<EquipmentModel> equipments) {
		for(int index = 0; index < equipments.size(); index++) {
			equipments.get(index).setUpperCategory(equipmentService.exchangeCodeToKoreanCategoryName(equipments.get(index).getUpperCategory()));
			equipments.get(index).setLowerCategory(equipmentService.exchangeCodeToKoreanCategoryName(equipments.get(index).getLowerCategory()));
		}
		
		return equipments;
	}
}
