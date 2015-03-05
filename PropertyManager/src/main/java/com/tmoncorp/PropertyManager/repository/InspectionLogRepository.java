package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmoncorp.PropertyManager.model.InspectionLogModel;

@Repository
public class InspectionLogRepository {
	@Autowired
	private SqlSession sqlSession;

	public int insertLog(InspectionLogModel inspectionLogModel) {
		InspectionLogMapper inspectionLogMapper = sqlSession.getMapper(InspectionLogMapper.class);
		int result = inspectionLogMapper.insertLog(inspectionLogModel);
		return result;
	}

	public List<InspectionLogModel> selectInspectedDataOnMember(int nth, String adAccount) {
		InspectionLogMapper inspectionLogMapper = sqlSession.getMapper(InspectionLogMapper.class);
		List<InspectionLogModel> result = inspectionLogMapper.selectInspectedDataOnMember(nth, adAccount);
		return result;
	}

	public int deleteInspectedDataOnMember(int nth, String adAccount) {
		InspectionLogMapper inspectionLogMapper = sqlSession.getMapper(InspectionLogMapper.class);
		int result = inspectionLogMapper.deleteInspectedDataOnMember(nth, adAccount);
		return result;
	}

	public String selectInspectedDataOfProperty(int nth, String propertyNumber) {
		InspectionLogMapper inspectionLogMapper = sqlSession.getMapper(InspectionLogMapper.class);
		return inspectionLogMapper.selectInspedtedDataOfProperty(nth, propertyNumber);
	}
}
