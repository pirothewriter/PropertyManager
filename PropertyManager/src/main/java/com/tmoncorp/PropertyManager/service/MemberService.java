package com.tmoncorp.PropertyManager.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.repository.MemberRepository;

/**
 * 
 * @author piro
 *
 */

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public List<MemberModel> selectMembers(int page) {
		return memberRepository.selectMembers(calculatePageToRow(page));
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

	public List<MemberModel> getRetiredMembers(int page) {
		return memberRepository.selectRetiredMembers(calculatePageToRow(page));
	}

	public int getMaximumPage() {
		return memberRepository.selectMaximumPage();
	}

	private int calculatePageToRow(int page) {
		return (page-1) * 10;
	}

	private MemberModel parsememberInfomation(HttpServletRequest request) {
		MemberModel memberModel = new MemberModel();
	
		memberModel.setMemberId(request.getParameter("memberId"));
		memberModel.setMemberName(request.getParameter("memberName"));
		memberModel.setUpperDivision(request.getParameter("upperDivision"));
		memberModel.setLowerDivision(request.getParameter("lowerDivision"));
		memberModel.setAdAccount(request.getParameter("adAccount"));
		memberModel.setOfficePhoneNumber(Integer.parseInt(request.getParameter("officePhoneNumber")));
	
		return memberModel;
	}

	public int getMaximumPageRetired() {
		return memberRepository.selectMaximumPageRetired();
	}
}
