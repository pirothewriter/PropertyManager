package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmoncorp.PropertyManager.model.MemberModel;

/**
 * 
 * @author piro
 *
 */

@Repository
public class MemberRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<MemberModel> selectAllMembers(){
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return memberMapper.selectAllMembers();
	}
	
	public int insertMemberInfomation(MemberModel memberModel){
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		int result = memberMapper.insertMemberInfomation(memberModel);
		return result;
	}
}
