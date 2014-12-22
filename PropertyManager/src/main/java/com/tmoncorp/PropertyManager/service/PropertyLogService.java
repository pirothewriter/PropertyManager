package com.tmoncorp.PropertyManager.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
