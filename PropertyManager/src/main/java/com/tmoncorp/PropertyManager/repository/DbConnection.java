package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmoncorp.PropertyManager.model.TestModel;

@Repository
public class DbConnection {
	@Autowired
	private SqlSession sqlSession;
	
	public List<TestModel> select(){
		TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
		return testMapper.selectTest();
	}
}
