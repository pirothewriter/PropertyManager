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

	public List<MemberModel> selectMembers(int page, int viewSolePage) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return memberMapper.selectMembers(page, viewSolePage);
	}

	public int insertMemberInfomation(MemberModel memberModel) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		int result = memberMapper.insertMemberInfomation(memberModel);
		return result;
	}

	public List<String> selectUpperDivisions() {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return memberMapper.selectUpperDivisions();
	}

	public List<String> selectLowerDivisions(String upperDivision) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return memberMapper.selectLowerDivisions(upperDivision);
	}

	public MemberModel selectAMember(String memberId) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return memberMapper.selectAMember(memberId);
	}

	public int modifyMemberInformation(MemberModel parsememberInfomation) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return memberMapper.modifyMember(parsememberInfomation);
	}

	public int retireMember(String memberId) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return memberMapper.retireMember(memberId);
	}

	public List<MemberModel> selectRetiredMembers(int page, int viewSolePage) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return memberMapper.selectRetiredMembers(page, viewSolePage);
	}

	public int selectMaximumPage(int viewSolePage) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return (memberMapper.selectMaximumRow() / viewSolePage) + 1;
	}

	public int selectMaximumPageRetired(int viewSolePage) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		return (memberMapper.selectMaximumRowRetired() / viewSolePage) + 1;
	}

}
