package com.tmoncorp.PropertyManager.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.model.MemberModel;
import com.tmoncorp.PropertyManager.model.PropertyLogModel;
import com.tmoncorp.PropertyManager.repository.MemberRepository;
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

	@Autowired
	private MemberRepository memberRepository;

	public void setPropertyLogRepository(PropertyLogRepository propertyLog) {
		propertyLogRepository = propertyLog;
	}

	public Date getPropertyUrgentDateNow(String propertyNumber, String adAccount) {
		return (Date) propertyLogRepository.getPropertyNowStatus(propertyNumber, adAccount);
	}

	public int urgentProperty(String adAccount, String propertyNumber) {
		int result = propertyLogRepository.insertUrgentProperty(adAccount, propertyNumber);
		return result;
	}

	public int urgentPropertyLog(String adAccount, String propertyNumber) {
		int result = propertyLogRepository.insertUrgentPropertyLog(adAccount, propertyNumber);
		return result;
	}

	public int releaseProperty(String adAccount, String propertyNumber) {
		int result = propertyLogRepository.deleteReleaseProperty(adAccount, propertyNumber);
		return result;
	}

	public int releasePropertyLog(String adAccount, String propertyNumber) {
		int result = propertyLogRepository.updateReleaseLogProperty(adAccount, propertyNumber);
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

	public int withdrawEqiupmentThatOwnedRetireMember(String adAccount) {
		int result = propertyLogRepository.releaseAllEquipmentOnRetireMember(adAccount);
		return result;
	}

	public int withdrawEqiupmentThatOwnedRetireMemberLog(String adAccount) {
		int result = propertyLogRepository.logWithdrawDateOfRetireMembersEquipment(adAccount);
		return result;
	}

	public List<PropertyLogModel> getPersonalLog(String adAccount) {
		List<PropertyLogModel> result = propertyLogRepository.selectPersonalLog(adAccount);
		for (int index = 0; index < result.size(); index++) {
			if (result.get(index).getUrgentDate() != null)
				result.get(index).setUrgentDate(new java.sql.Date(result.get(index).getUrgentDate().getTime()));
			if (result.get(index).getWithdrawDate() != null)
				result.get(index).setWithdrawDate(new java.sql.Date(result.get(index).getWithdrawDate().getTime()));
		}

		return result;
	}

	public String getPropertyInfomation(String propertyNumber) {
		PropertyLogModel mappedInfo = propertyLogRepository.getPropertyInfomation(propertyNumber);

		if (mappedInfo == null)
			return "NO_EXIST";
		else {
			if (mappedInfo.getAdAccount() == null)
				return "SUCCESS";
			else {
				MemberModel member = memberRepository.selectAMember(mappedInfo.getAdAccount());
				return member.getMemberName() + "(" + member.getUpperDivision() + ")";
			}
		}
	}

}
