package com.tmoncorp.PropertyManager.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.model.CategoryModel;
import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.repository.MemberRepository;

/**
 * 
 * @author piro
 *
 */

@Service
public class MemberService {
	private static final int NO_EXIST_CATEGORY_CODE = -1;
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private CategoryService categoryService;

	public List<MemberModel> selectMembers(int page, int viewSolePage, String upperCategory, String lowerCategory, String adAccount, String nameOfMember) {
		return memberRepository.selectMembers(calculatePageToRow(page, viewSolePage), viewSolePage, upperCategory, lowerCategory, adAccount, nameOfMember);
	}

	public List<String> getUpperDivisions() {
		return memberRepository.selectUpperDivisions();
	}

	public List<String> getLowerDivisions(String upperDivision) {
		return memberRepository.selectLowerDivisions(upperDivision);
	}

	public int insertMemberInfomation(HttpServletRequest request) {
		int result = memberRepository.insertMemberInfomation(parsememberInfomation(request));
		return result;
	}

	public MemberModel selectAMember(String memberId) {
		return memberRepository.selectAMember(memberId);
	}

	public int modifyMemberInformation(HttpServletRequest request) {
		int result = memberRepository.modifyMemberInformation(parsememberInfomation(request));
		return result;
	}

	public int retireMember(String memberId) {
		int result = memberRepository.retireMember(memberId);
		return result;
	}

	public List<MemberModel> getRetiredMembers(int page, int viewSolePage, String upperCategory, String lowerCategory, String adAccount, String nameOfMember) {
		return memberRepository.selectRetiredMembers(calculatePageToRow(page, viewSolePage), viewSolePage, upperCategory, lowerCategory, adAccount, nameOfMember);
	}

	public int getMaximumPage(int viewSolePage, String upperCategory, String lowerCategory, String adAccount, String nameOfMember) {
		return memberRepository.selectMaximumPage(viewSolePage, upperCategory, lowerCategory, adAccount, nameOfMember);
	}

	private int calculatePageToRow(int page, int viewSolePage) {
		return (page - 1) * viewSolePage;
	}

	private MemberModel parsememberInfomation(HttpServletRequest request) {
		MemberModel memberModel = new MemberModel();

		memberModel.setMemberId(request.getParameter("memberId"));
		memberModel.setMemberName(request.getParameter("memberName"));
		memberModel.setUpperDivision(request.getParameter("upperDivision"));
		memberModel.setLowerDivision(request.getParameter("lowerDivision"));
		memberModel.setAdAccount(request.getParameter("adAccount"));
		memberModel.setOfficePhoneNumber(Integer.parseInt(request.getParameter("officePhoneNumber")));

		memberModel = setDivisionCode(memberModel);

		return memberModel;
	}

	public int getMaximumPageRetired(int viewSolePage, String upperCategory, String lowerCategory, String adAccount, String nameOfMember) {
		return memberRepository.selectMaximumPageRetired(viewSolePage, upperCategory, lowerCategory, adAccount, nameOfMember);
	}

	private MemberModel setDivisionCode(MemberModel memberModel) {
		List<CategoryModel> upperCategories = categoryService.getAllUpperCategory();
		List<CategoryModel> lowerCategories = categoryService.getAllLowerCategory();

		String upperDivisionName = memberModel.getUpperDivision();
		String lowerDivisionName = memberModel.getLowerDivision();

		int upperDivisionCode = convertDivisionNameToCode(upperDivisionName, upperCategories);

		if (upperDivisionCode == NO_EXIST_CATEGORY_CODE)
			upperDivisionCode = insertedNewCode(upperDivisionName, "upper", 0);

		int lowerDivisionCode = convertDivisionNameToCode(lowerDivisionName, lowerCategories);

		if (lowerDivisionCode == NO_EXIST_CATEGORY_CODE)
			lowerDivisionCode = insertedNewCode(lowerDivisionName, "lower", upperDivisionCode);

		memberModel.setUpperDivisionCode(upperDivisionCode);
		memberModel.setLowerDivisionCode(lowerDivisionCode);

		return memberModel;
	}

	private int insertedNewCode(String divisionName, String divisionType, int upperDivisionCode) {
		if (divisionType.compareTo("upper") == 0) {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setCategoryName(divisionName);
			categoryService.insertUpperCategory(categoryModel);
		} else if (divisionType.compareTo("lower") == 0) {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setCategoryName(divisionName);
			categoryModel.setUpperCategory(upperDivisionCode);
			categoryService.insertLowerCategory(categoryModel);
		}

		return categoryService.selectSpecificCategory(divisionName);
	}

	private int convertDivisionNameToCode(String divisionName, List<CategoryModel> categories) {
		int code = NO_EXIST_CATEGORY_CODE;

		for (int index = 0; index < categories.size(); index++) {
			if (categories.get(index).getCategoryName().compareTo(divisionName) == 0)
				code = categories.get(index).getCategoryCode();
		}

		return code;
	}
}
