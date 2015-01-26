package com.tmoncorp.PropertyManager.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.model.EquipmentModel;
import com.tmoncorp.PropertyManager.repository.CsvReaderRepository;
import com.tmoncorp.PropertyManager.repository.EquipmentRepository;
import com.tmoncorp.PropertyManager.util.ExchangeCharacterSet;
import com.tmoncorp.PropertyManager.util.ExchangeDateBetweenString;

/**
 * 
 * @author piro
 *
 */

@Service
public class EquipmentService {
	@Autowired
	private EquipmentRepository equipmentRepository;

	private CsvReaderRepository csvReaderRepository;

	public int equipmentInsertion(HttpServletRequest request) throws ParseException {
		int affectedRows = equipmentRepository.insertEquipmentInfomation(parsingInsertionParameters(request));
		return affectedRows;
	}

	public List<EquipmentModel> selectPropertyOnMember(String adAccount) {
		return equipmentRepository.selectPropertyOnMember(adAccount);
	}

	public List<EquipmentModel> getOwnerlessEquipment(int nowPage, int viewSolePage, String upperCategory, String lowerCategory) {
		return equipmentRepository.selectOwnerlessEquipments(calculatePageToRow(nowPage, viewSolePage), viewSolePage, upperCategory, lowerCategory);
	}

	public int insertMultipleEquipment(File csvFile) throws IOException, ParseException {
		csvReaderRepository = new CsvReaderRepository();
		int insertSuccess = 0;

		List<String[]> parsedList = csvReaderRepository.parsingCsv(csvFile);

		for (int index = 1; index < parsedList.size(); index++) {
			String[] parsedData = parsedList.get(index);
			ExchangeDateBetweenString exchangeDateBetweenString = new ExchangeDateBetweenString();
			EquipmentModel equipment = generateEquipmentData(parsedData, exchangeDateBetweenString);

			insertSuccess += equipmentRepository.insertEquipmentInfomation(equipment);
		}

		return insertSuccess;
	}

	private EquipmentModel parsingInsertionParameters(HttpServletRequest request) throws ParseException {
		EquipmentModel dataForInsert = new EquipmentModel();
		ExchangeDateBetweenString exchangeDateBetweenString = new ExchangeDateBetweenString();

		dataForInsert.setPropertyNumber(request.getParameter("propertyHeadNumber") + request.getParameter("propertyNumber"));
		dataForInsert.setName(request.getParameter("propertyName"));
		dataForInsert.setUpperCategory(request.getParameter("propertyUpperCategory"));
		dataForInsert.setLowerCategory(request.getParameter("propertyLowerCategory"));
		dataForInsert.setInfomation1(request.getParameter("propertyInformation1"));
		dataForInsert.setInfomation2(request.getParameter("propertyInformation2"));
		dataForInsert.setIncommingItUnit(exchangeDateBetweenString.stringToDate(request.getParameter("propertyIncommingITUnit")));
		dataForInsert.setIncommingFinance(exchangeDateBetweenString.stringToDate(request.getParameter("propertyIncommingFinance")));
		dataForInsert.setProductor(request.getParameter("propertyProducted"));
		dataForInsert.setSeller(request.getParameter("propertySeller"));
		dataForInsert.setPrice(Integer.parseInt(request.getParameter("propertyPrice")));
		dataForInsert.setUser("티켓몬스터");

		return dataForInsert;
	}

	private EquipmentModel generateEquipmentData(String[] parsedData, ExchangeDateBetweenString exchangeDateBetweenString) throws ParseException, IOException {
		EquipmentModel equipment = new EquipmentModel();
		ExchangeCharacterSet exchangeCharacterSet = new ExchangeCharacterSet();

		equipment.setPropertyNumber(exchangeCharacterSet.convert(parsedData[0], "UTF-8"));
		equipment.setUpperCategory(exchangeCharacterSet.convert(parsedData[1], "UTF-8"));
		equipment.setLowerCategory(exchangeCharacterSet.convert(parsedData[2], "UTF-8"));
		equipment.setName(exchangeCharacterSet.convert(parsedData[3], "UTF-8"));
		equipment.setInfomation1(exchangeCharacterSet.convert(parsedData[4], "UTF-8"));
		equipment.setInfomation2(exchangeCharacterSet.convert(parsedData[5], "UTF-8"));
		equipment.setIncommingItUnit(exchangeDateBetweenString.stringToDate(exchangeCharacterSet.convert(parsedData[6], "UTF-8")));
		equipment.setIncommingFinance(exchangeDateBetweenString.stringToDate(exchangeCharacterSet.convert(parsedData[7], "UTF-8")));
		equipment.setProductor(exchangeCharacterSet.convert(parsedData[8], "UTF-8"));
		equipment.setSeller(exchangeCharacterSet.convert(parsedData[9], "UTF-8"));
		equipment.setPrice(Integer.parseInt(exchangeCharacterSet.convert(parsedData[10], "UTF-8")));
		equipment.setUser("티켓몬스터");
		return equipment;
	}

	public int getMaximumPage(int viewSolePage) {
		return ((equipmentRepository.selectAllOwnerlessEquipment() - 1) / viewSolePage) + 1;
	}

	private int calculatePageToRow(int page, int viewSolePage) {
		return (page - 1) * viewSolePage;
	}
}
