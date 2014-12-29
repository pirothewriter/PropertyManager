package com.tmoncorp.PropertyManager.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.repository.CsvReaderRepository;
import com.tmoncorp.PropertyManager.repository.MemberRepository;
import com.tmoncorp.PropertyManager.util.ExchangeCharacterSet;

@Service
public class CsvReaderService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private CsvReaderRepository csvReaderRepository;
	
	public void multipleMemberInsert(String csvFile) throws IOException {
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
