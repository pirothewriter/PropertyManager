package com.tmoncorp.PropertyManager.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.repository.CsvReaderRepository;
import com.tmoncorp.PropertyManager.repository.MemberRepository;
import com.tmoncorp.PropertyManager.util.ExchangeCharacterSet;

/**
 * 
 * @author piro
 *
 */

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public List<MemberModel> selectMembers() {
		return memberRepository.selectMembers();
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

	public int modifyMemberInformation(HttpServletRequest request) {
		int result = memberRepository.modifyMemberInformation(parsememberInfomation(request));
		return result;
	}

	public int retireMember(String memberId) {
		int result = memberRepository.retireMember(memberId);
		return result;
	}

	public List<MemberModel> getRetiredMembers() {
		return memberRepository.selectRetiredMembers();
	}

	public void multipleMemberInsert(String csvFile) throws IOException {
		CsvReaderRepository csvReaderRepository = new CsvReaderRepository();
		List<String[]> membersList = csvReaderRepository.parsingCsv(csvFile);

		ExchangeCharacterSet exchangeCharacterSet = new ExchangeCharacterSet();

		for (int index = 1; index < membersList.size(); index++) {
			String[] member = membersList.get(index);
			convertUtf8(exchangeCharacterSet, member);
			MemberModel model = generateMemberModel(member);

			memberRepository.insertMemberInfomation(model);
		}
	}

	private void convertUtf8(ExchangeCharacterSet exchangeCharacterSet, String[] member) throws IOException {
		for (int index = 0; index < member.length; index++)
			member[index] = exchangeCharacterSet.convert(member[index], "UTF-8");
	}

	private MemberModel generateMemberModel(String[] csvParsedString) {
		MemberModel memberModel = new MemberModel();
		memberModel.setMemberId(csvParsedString[0]);
		memberModel.setMemberName(csvParsedString[1]);
		memberModel.setUpperDivision(csvParsedString[2]);
		memberModel.setLowerDivision(csvParsedString[3]);
		memberModel.setAdAccount(csvParsedString[4]);
		memberModel.setOfficePhoneNumber(Integer.parseInt(csvParsedString[5]));
		return memberModel;
	}
}
