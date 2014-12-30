package com.tmoncorp.PropertyManager.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.model.EquipmentModel;
import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.repository.CsvReaderRepository;
import com.tmoncorp.PropertyManager.repository.EquipmentRepository;
import com.tmoncorp.PropertyManager.repository.MemberRepository;
import com.tmoncorp.PropertyManager.util.ExchangeCharacterSet;
import com.tmoncorp.PropertyManager.util.ExchangeDateBetweenString;

@Service
public class CsvReaderService {
	private static final int CSV_START_ROW = 1;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Autowired
	private CsvReaderRepository csvReaderRepository;

	public int multipleMemberInsert(File csvFile) throws IOException {
		List<String[]> membersList = csvReaderRepository.parsingCsv(csvFile);
		int result = 0;

		ExchangeCharacterSet exchangeCharacterSet = new ExchangeCharacterSet();

		for (int index = CSV_START_ROW; index < membersList.size(); index++) {
			String[] member = membersList.get(index);
			convertUtf8(exchangeCharacterSet, member);
			MemberModel model = generateMemberModel(member);

			result += memberRepository.insertMemberInfomation(model);
		}

		return result;
	}

	public int multiplePropertiesInsert(File csvFile) throws IOException, ParseException {
		List<String[]> properties = csvReaderRepository.parsingCsv(csvFile);
		int result = 0;

		ExchangeCharacterSet exchangeCharacterSet = new ExchangeCharacterSet();

		for (int index = CSV_START_ROW; index < properties.size(); index++) {
			String[] property = properties.get(index);
			convertUtf8(exchangeCharacterSet, property);
			EquipmentModel equipment = genearateEquipmentModel(property);

			result += equipmentRepository.insertEquipmentInfomation(equipment);
		}

		return result;
	}

	private EquipmentModel genearateEquipmentModel(String[] property) throws ParseException {
		EquipmentModel equipmentModel = new EquipmentModel();
		ExchangeDateBetweenString exchangeDateBetweenString = new ExchangeDateBetweenString();
		equipmentModel.setPropertyNumber(property[0]);
		equipmentModel.setUpperCategory(property[1]);
		equipmentModel.setLowerCategory(property[2]);
		equipmentModel.setName(property[3]);
		equipmentModel.setInfomation1(property[4]);
		equipmentModel.setInfomation2(property[5]);
		equipmentModel.setIncommingItUnit(exchangeDateBetweenString.stringToDate(property[6]));
		equipmentModel.setIncommingFinance(exchangeDateBetweenString.stringToDate(property[7]));
		equipmentModel.setProductor(property[8]);
		equipmentModel.setSeller(property[9]);
		equipmentModel.setPrice(Integer.parseInt(property[10]));
		equipmentModel.setUser("티켓몬스터");

		return equipmentModel;
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