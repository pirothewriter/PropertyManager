package com.tmoncorp.PropertyManager.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.model.EquipmentModel;
import com.tmoncorp.PropertyManager.repository.EquipmentRepository;
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

	public int equipmentInsertion(HttpServletRequest request) throws ParseException {
		int affectedRows = equipmentRepository.insertEquipmentInfomation(parsingInsertionParameters(request));
		return affectedRows;
	}
	
	public List<EquipmentModel> selectPropertyOnMember(String memberId){
		return equipmentRepository.selectPropertyOnMember(memberId);
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
}
