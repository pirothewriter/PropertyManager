package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmoncorp.PropertyManager.model.InspectionModel;

/**
 * 
 * @author pirothewriter
 *
 */

@Repository
public class InspectionRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<InspectionModel> selectInspectedData(int page, int viewSolePage, int nth, String adAccount, String memberName, char flagDifference) {
		InspectionMapper inspectionMapper = sqlSession.getMapper(InspectionMapper.class);
		List<InspectionModel> result = inspectionMapper.selectInspectedData(page, viewSolePage, nth, adAccount, memberName, flagDifference);
		return result;
	}

	public int insertInspection(InspectionModel inspectionModel) {
		InspectionMapper inspectionMapper = sqlSession.getMapper(InspectionMapper.class);
		int result = inspectionMapper.insertInspection(inspectionModel);
		return result;
	}

	public int deleteInspectedData(int nth, String adAccount, String propertyNumber) {
		InspectionMapper inspectionMapper = sqlSession.getMapper(InspectionMapper.class);
		int result = inspectionMapper.deleteInspectedData(nth, adAccount, propertyNumber);
		return result;
	}

	public int getMaximumPage(int viewSolePage, int nth, String adAccount, String memberName, char flagDifference) {
		InspectionMapper inspectionMapper = sqlSession.getMapper(InspectionMapper.class);
		return (inspectionMapper.getMaximumPage(nth, adAccount, memberName, flagDifference) / viewSolePage) + 1;
	}

	public InspectionModel selectInspectedData(int nth, String propertyNumber) {
		InspectionMapper inspectionMapper = sqlSession.getMapper(InspectionMapper.class);
		return inspectionMapper.getThisNthPropertyInspectedData(nth, propertyNumber);
	}
	
	public List<InspectionModel> generateCsvFile(int nth, String adAccount, String memberName, String flagDifference){
		InspectionMapper inspectionMapper = sqlSession.getMapper(InspectionMapper.class);
		return inspectionMapper.getAllInspectionListOnCondition(nth, adAccount, memberName, flagDifference);
	}
}
