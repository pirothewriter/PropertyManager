package com.tmoncorp.PropertyManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.model.EquipmentModel;
import com.tmoncorp.PropertyManager.model.InspectionModel;
import com.tmoncorp.PropertyManager.model.InspectionNthModel;
import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.model.PropertyLogModel;
import com.tmoncorp.PropertyManager.repository.EquipmentRepository;
import com.tmoncorp.PropertyManager.repository.InspectionLogRepository;
import com.tmoncorp.PropertyManager.repository.InspectionNthRepository;
import com.tmoncorp.PropertyManager.repository.InspectionRepository;
import com.tmoncorp.PropertyManager.repository.MemberRepository;
import com.tmoncorp.PropertyManager.repository.PropertyLogRepository;
import com.tmoncorp.PropertyManager.util.JsonEncoding;

/**
 * 
 * @author pirothewriter
 *
 */

@Service
public class InspectionService {
	@Autowired
	private InspectionRepository inspectionRepository;

	@Autowired
	private InspectionLogRepository inspectionLogRepository;

	@Autowired
	private InspectionNthRepository inspectionNthRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Autowired
	private PropertyLogRepository propertyLogRepository;

	public List<InspectionModel> selectInspections(int page, int viewSolePage, int nth, String adAccount, String memberName, char flagDifference) {
		return inspectionRepository.selectInspectedData(page, viewSolePage, nth, adAccount, memberName, flagDifference);
	}

	public int getMaximumPage(int viewSolePage, int nth, String adAccount, String memberName, char flagDifference) {
		return inspectionRepository.getMaximumPage(viewSolePage, nth, adAccount, memberName, flagDifference);
	}

	public String getPropertyInfomation(int nth, String propertyNumber) {
		String result = "";
		JsonEncoding jsonEncoding = new JsonEncoding();

		if (inspectionRepository.selectInspectedData(nth, propertyNumber) != null) {
			result = "ALREADY_CHECKED";
		} else {
			EquipmentModel targetProperty = equipmentRepository.getPropertyInfomation(propertyNumber);
			if (targetProperty == null)
				result = "NOT_EXIST";
			else {
				InspectionModel inspectionModel = new InspectionModel();
				inspectionModel.setPropertyNumber(propertyNumber);
				inspectionModel.setPropertyName(targetProperty.getName());
				
				PropertyLogModel mappedInfo = propertyLogRepository.getMappedInfomation(propertyNumber);
				if (mappedInfo == null){
					result = jsonEncoding.encodingJsonForInspect(inspectionModel);
				}
				else {
					MemberModel member = memberRepository.selectAMember(mappedInfo.getAdAccount());
					inspectionModel.setAdAccount(member.getAdAccount());
					inspectionModel.setMemberName(member.getMemberName() + "(" + member.getUpperDivision() + ")");
					result = jsonEncoding.encodingJsonForInspect(inspectionModel);
				}
			}
		}
		return result;
	}

	public int getLastestNth() {
		InspectionNthModel lastestNth = inspectionNthRepository.getLastestNth();

		if (lastestNth == null) {
			inspectionNthRepository.createNewNth(1);
		} else if (lastestNth.getEndDate() != null) {
			inspectionNthRepository.createNewNth(lastestNth.getNth() + 1);
		}

		lastestNth = inspectionNthRepository.getLastestNth();

		return lastestNth.getNth();
	}

	public List<InspectionNthModel> getNthList() {
		return inspectionNthRepository.selectAllNth();
	}

	public int endNth(int nth) {
		return inspectionNthRepository.endNth(nth);
	}
}
