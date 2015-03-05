package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmoncorp.PropertyManager.model.InspectionNthModel;

/**
 * 
 * @author pirothewriter
 *
 */

@Repository
public class InspectionNthRepository {
	@Autowired
	private SqlSession sqlSession;

	public InspectionNthModel getLastestNth() {
		InspectionNthMapper inspectionNthMapper = sqlSession.getMapper(InspectionNthMapper.class);
		return inspectionNthMapper.selectLastestNth();
	}

	public int createNewNth(int nextNth) {
		InspectionNthMapper inspectionNthMapper = sqlSession.getMapper(InspectionNthMapper.class);
		int result = inspectionNthMapper.insertNewNth(nextNth);
		return result;
	}

	public List<InspectionNthModel> selectAllNth() {
		InspectionNthMapper inspectionNthMapper = sqlSession.getMapper(InspectionNthMapper.class);
		return inspectionNthMapper.selectAllNth();
	}
	
	public int endNth(int nth){
		InspectionNthMapper inspectionNthMapper = sqlSession.getMapper(InspectionNthMapper.class);
		return inspectionNthMapper.endNth(nth);
	}
}
