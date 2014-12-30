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
import com.tmoncorp.PropertyManager.repository.PropertyLogRepository;
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
	private PropertyLogRepository propertyLogRepository;

	@Autowired
	private CsvReaderRepository csvReaderRepository;

	public int multipleMemberInsert(File csvFile) throws IOException {
		List<String[]> membersList = csvReaderRepository.parsingCsv(csvFile);
		int result = 0;

		for (int index = CSV_START_ROW; index < membersList.size(); index++) {
			String[] member = membersList.get(index);
			member = convertUtf8(member);
			MemberModel model = generateMemberModel(member);

			result += memberRepository.insertMemberInfomation(model);
		}

		return result;
	}

	public int multiplePropertiesInsert(File csvFile) throws IOException, ParseException {
		List<String[]> properties = csvReaderRepository.parsingCsv(csvFile);
		int result = 0;

		for (int index = CSV_START_ROW; index < properties.size(); index++) {
			String[] property = properties.get(index);
			property = convertUtf8(property);
			EquipmentModel equipment = genearateEquipmentModel(property);

			result += equipmentRepository.insertEquipmentInfomation(equipment);
		}

		return result;
	}

	public int multipleMappedInsert(File csvFile) throws IOException, ParseException {
		int result = 0;
		List<String[]> mapped = csvReaderRepository.parsingCsv(csvFile);
		MemberModel memberModel = new MemberModel();
		EquipmentModel equipmentModel = new EquipmentModel();

		for (int index = CSV_START_ROW; index < mapped.size(); index++) {
			String[] tempMember = new String[6];
			String[] tempProperties = new String[11];

			String[] tempArray = mapped.get(index);
			tempArray = convertUtf8(tempArray);
			System.arraycopy(tempArray, 0, tempMember, 0, 6);
			System.arraycopy(tempArray, 6, tempProperties, 0, 11);

			if (checkNullValue(tempMember) == true) {
				memberModel = generateMemberModel(tempMember);
				memberRepository.insertMemberInfomation(memberModel);
				result++;
			}
			if (checkNullValue(tempProperties) == true) {
				equipmentModel = genearateEquipmentModel(tempProperties);
				equipmentRepository.insertEquipmentInfomation(equipmentModel);
				result++;
			}

			if (checkNullValue(tempMember) == true && checkNullValue(tempProperties) == true) {
				propertyLogRepository.insertUrgentProperty(memberModel.getMemberId(), equipmentModel.getPropertyNumber());
				propertyLogRepository.insertUrgentPropertyLog(memberModel.getMemberId(), equipmentModel.getPropertyNumber());
				result++;
			}
		}

		return result;
	}

	public boolean verifyForm(File csvFile, String inputType) throws IOException {
		String[] head = csvReaderRepository.parsingCsv(csvFile).get(0);
		String[] correctForm;

		if (inputType.compareTo("member") == 0) {
			String tmp[] = { "사원번호", "사원명", "부서명(大)", "부서명(小)", "AD계정", "내선번호" };
			correctForm = tmp;
		} else if (inputType.compareTo("property") == 0) {
			String tmp[] = { "자산번호", "자산구분(大)", "자산구분(小)", "자산명", "자산정보1", "자산정보2", "자산입고일(IT유닛 입고 당일)", "자산입고일(재무팀 월말)", "자산제조사", "자산판매사", "자산구매단가" };
			correctForm = tmp;
		} else if (inputType.compareTo("mapped") == 0) {
			String tmp[] = { "사원번호", "사원명", "부서명(大)", "부서명(小)", "AD계정", "내선번호", "자산번호", "자산구분(大)", "자산구분(小)", "자산명", "자산정보1", "자산정보2", "자산입고일(IT유닛 입고 당일)", "자산입고일(재무팀 월말)", "자산제조사", "자산판매사", "자산구매단가" };
			correctForm = tmp;
		} else {
			return false;
		}

		head = convertUtf8(head);
		correctForm = convertUtf8(correctForm);

		return (head.length == correctForm.length);
	}

	private boolean checkNullValue(String[] parsedArray) {
		boolean isNotNull = true;
		for (int index = 0; index < parsedArray.length; index++) {
			if (parsedArray[index] == "")
				isNotNull = false;
		}

		return isNotNull;
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

	private String[] convertUtf8(String[] arrayFromCsv) throws IOException {
		ExchangeCharacterSet exchangeCharacterSet = new ExchangeCharacterSet();
		for (int index = 0; index < arrayFromCsv.length; index++)
			arrayFromCsv[index] = exchangeCharacterSet.convert(arrayFromCsv[index], "UTF-8");

		return arrayFromCsv;
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
