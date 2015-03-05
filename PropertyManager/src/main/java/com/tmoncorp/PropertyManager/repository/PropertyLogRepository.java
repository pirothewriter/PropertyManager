package com.tmoncorp.PropertyManager.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmoncorp.PropertyManager.model.PropertyLogModel;

/**
 * 
 * @author piro
 *
 */

@Repository
public class PropertyLogRepository {
	@Autowired
	private SqlSession sqlSession;

	public Date getPropertyNowStatus(String propertyNumber, String adAccount) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		Date result = propertyLogMapper.selectPropertyNow(propertyNumber, adAccount);
		return new java.sql.Date(result.getTime());
	}

	public int insertUrgentProperty(String adAccount, String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.insertUrgentProperty(adAccount, propertyNumber);
	}

	public int insertUrgentPropertyLog(String adAccount, String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.insertUrgentPropertyLog(adAccount, propertyNumber);
	}

	public int deleteReleaseProperty(String adAccount, String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.deleteReleaseProperty(adAccount, propertyNumber);
	}

	public int updateReleaseLogProperty(String adAccount, String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.updateReleaseLogProperty(adAccount, propertyNumber);
	}

	public List<PropertyLogModel> selectEquipmentLog(String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.selectPropertyLog(propertyNumber);
	}

	public int releaseAllEquipmentOnRetireMember(String adAccount) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.releaseAllEquipmentOnRetireMember(adAccount);
	}

	public int logWithdrawDateOfRetireMembersEquipment(String adAccount) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.logWithdrawDateOfRetireMembersEquipment(adAccount);
	}

	public List<PropertyLogModel> selectPersonalLog(String adAccount) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.selectPersonalLog(adAccount);
	}

	public PropertyLogModel getPropertyInfomation(String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		List<PropertyLogModel> result = propertyLogMapper.getPropertyInfomation(propertyNumber);
		if (result.size() > 0)
			return result.get(0);
		else
			return null;
	}

	public PropertyLogModel getMappedInfomation(String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.getMappedInfomation(propertyNumber);
	}

}
