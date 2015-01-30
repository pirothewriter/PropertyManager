package com.tmoncorp.PropertyManager.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author piro
 *
 */

@Repository
public class SecurityRepository {
	@Autowired
	private SqlSession sqlSession;

	public int insertUser(String userName, String password) {
		SecurityMapper securityMapper = sqlSession.getMapper(SecurityMapper.class);
		return securityMapper.insertUser(userName, password);
	}
}
