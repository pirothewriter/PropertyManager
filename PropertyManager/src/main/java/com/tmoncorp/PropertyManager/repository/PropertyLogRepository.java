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

	public List<PropertyLogModel> getPropertyLog(String propertyNumber) {
		PropertyLogMapper propertyLogMapper = sqlSession.getMapper(PropertyLogMapper.class);
		return propertyLogMapper.selectPropertyLog(propertyNumber);
	}

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

}
