package com.tmoncorp.PropertyManager.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.model.PropertyLogModel;
import com.tmoncorp.PropertyManager.repository.PropertyLogRepository;

/**
 * 
 * @author piro
 *
 */

@Service
public class PropertyLogService {
	@Autowired
	private PropertyLogRepository propertyLogRepository;

	public Date getPropertyUrgentDateNow(String propertyNumber, String memberId) {
		return (Date) propertyLogRepository.getPropertyNowStatus(propertyNumber, memberId);
	}

	public int urgentProperty(String memberId, String propertyNumber) {
		int result = propertyLogRepository.insertUrgentProperty(memberId, propertyNumber);
		return result;
	}

	public int urgentPropertyLog(String memberId, String propertyNumber) {
		int result = propertyLogRepository.insertUrgentPropertyLog(memberId, propertyNumber);
		return result;
	}

	public int releaseProperty(String memberId, String propertyNumber) {
		int result = propertyLogRepository.deleteReleaseProperty(memberId, propertyNumber);
		return result;
	}

	public int releasePropertyLog(String memberId, String propertyNumber) {
		int result = propertyLogRepository.updateReleaseLogProperty(memberId, propertyNumber);
		return result;
	}

	public List<PropertyLogModel> getEquipmentLog(String propertyNumber) {
		List<PropertyLogModel> result = propertyLogRepository.selectEquipmentLog(propertyNumber);
		for (int index = 0; index < result.size(); index++) {
			if (result.get(index).getUrgentDate() != null)
				result.get(index).setUrgentDate(new java.sql.Date(result.get(index).getUrgentDate().getTime()));
			if (result.get(index).getWithdrawDate() != null)
				result.get(index).setWithdrawDate(new java.sql.Date(result.get(index).getWithdrawDate().getTime()));
		}
		return result;
	}

	public int withdrawEqiupmentThatOwnedRetireMember(String memberId) {
		int result = propertyLogRepository.releaseAllEquipmentOnRetireMember(memberId);
		return result;
	}

	public int withdrawEqiupmentThatOwnedRetireMemberLog(String memberId) {
		int result = propertyLogRepository.logWithdrawDateOfRetireMembersEquipment(memberId);
		return result;
	}

}
