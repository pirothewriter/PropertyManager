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

	public int insertUser(String username) {
		SecurityMapper securityMapper = sqlSession.getMapper(SecurityMapper.class);
		int result = securityMapper.insertUser(username);
		result += securityMapper.insertAuthority(username);

		return result;
	}

	public String getPassword(String username) {
		SecurityMapper securityMapper = sqlSession.getMapper(SecurityMapper.class);
		return securityMapper.getPassword(username);
	}

	public String getRole(String username) {
		SecurityMapper securityMapper = sqlSession.getMapper(SecurityMapper.class);
		return securityMapper.getRole(username);
	}

	public int grantAdmin(String adAccount) {
		SecurityMapper securityMapper = sqlSession.getMapper(SecurityMapper.class);
		return securityMapper.grantAdmin(adAccount);
	}
	
	public int revokeAdmin(String adAccount) {
		SecurityMapper securityMapper = sqlSession.getMapper(SecurityMapper.class);
		return securityMapper.revokeAdmin(adAccount);
	}
	
	public int revokeUser(String adAccount) {
		SecurityMapper securityMapper = sqlSession.getMapper(SecurityMapper.class);
		return securityMapper.revokeUser(adAccount);
	}
}
