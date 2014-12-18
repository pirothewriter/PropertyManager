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
	
	public List<MemberModel> selectAllMembers(){
		return memberRepository.selectAllMembers();
	}
	
	public int insertMemberInfomation(HttpServletRequest request){
		int result = memberRepository.insertMemberInfomation(parsememberInfomation(request));
		return result;
	}
	
	private MemberModel parsememberInfomation(HttpServletRequest request){
		MemberModel memberModel = new MemberModel();
		
		memberModel.setMemberId(request.getParameter("memberId"));
		memberModel.setMemberName(request.getParameter("memberName"));
		memberModel.setUpperDivision(request.getParameter("upperDivision"));
		memberModel.setLowerDivision(request.getParameter("lowerDivision"));
		memberModel.setEmailAddress(request.getParameter("emailAddress"));
		memberModel.setOfficePhoneNumber(Integer.parseInt(request.getParameter("officePhoneNumber")));
		
		return memberModel;
	}

}
