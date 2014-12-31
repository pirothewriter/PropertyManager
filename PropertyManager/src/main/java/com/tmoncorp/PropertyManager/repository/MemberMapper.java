package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmoncorp.PropertyManager.model.MemberModel;

/**
 * 
 * @author piro
 *
 */

public interface MemberMapper {
	int insertMemberInfomation(MemberModel memberModel);

	List<MemberModel> selectMembers(@Param("page") int page, @Param("viewSolePage") int viewSolePage);

	List<String> selectUpperDivisions();

	List<String> selectLowerDivisions(String upperDivision);

	MemberModel selectAMember(String memberId);

	int modifyMember(MemberModel memberModel);

	int retireMember(String memberId);

	List<MemberModel> selectRetiredMembers(@Param("page") int page, @Param("viewSolePage") int viewSolePage);

	int selectMaximumRow();

	int selectMaximumRowRetired();

}
