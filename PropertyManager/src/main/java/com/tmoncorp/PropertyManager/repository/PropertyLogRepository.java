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

	public Date getPropertyNowStatus(String propertyNumber, String memberId) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		Date result = propertyLogMapper.selectPropertyNow(propertyNumber, memberId);
		return new java.sql.Date(result.getTime());
	}

	public int insertUrgentProperty(String memberId, String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.insertUrgentProperty(memberId, propertyNumber);
	}

	public int insertUrgentPropertyLog(String memberId, String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.insertUrgentPropertyLog(memberId, propertyNumber);
	}

	public int deleteReleaseProperty(String memberId, String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.deleteReleaseProperty(memberId, propertyNumber);
	}

	public int updateReleaseLogProperty(String memberId, String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.updateReleaseLogProperty(memberId, propertyNumber);
	}

	public List<PropertyLogModel> selectEquipmentLog(String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.selectPropertyLog(propertyNumber);
	}

	public int releaseAllEquipmentOnRetireMember(String memberId) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.releaseAllEquipmentOnRetireMember(memberId);
	}

	public int logWithdrawDateOfRetireMembersEquipment(String memberId) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.logWithdrawDateOfRetireMembersEquipment(memberId);
	}

	public List<PropertyLogModel> selectPersonalLog(String memberId) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.selectPersonalLog(memberId);
	}

}
