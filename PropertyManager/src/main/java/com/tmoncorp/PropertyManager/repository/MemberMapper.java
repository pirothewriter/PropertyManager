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

	List<MemberModel> selectMembers(@Param("page") int page, @Param("viewSolePage") int viewSolePage, @Param("upperCategory") String upperCategory, @Param("lowerCategory") String lowerCategory, @Param("adAccount") String adAccount, @Param("nameOfMember") String nameOfMember);

	List<String> selectUpperDivisions();

	List<String> selectLowerDivisions(String upperDivision);

	MemberModel selectAMember(String memberId);

	int modifyMember(MemberModel memberModel);

	int retireMember(String memberId);

	List<MemberModel> selectRetiredMembers(@Param("page") int page, @Param("viewSolePage") int viewSolePage, @Param("upperCategory") String upperCategory, @Param("lowerCategory") String lowerCategory, @Param("adAccount") String adAccount, @Param("nameOfMember") String nameOfMember);

	int selectMaximumRow(@Param("upperCategory") String upperCategory, @Param("lowerCategory") String lowerCategory, @Param("adAccount") String adAccount, @Param("nameOfMember") String nameOfMember);

	int selectMaximumRowRetired(@Param("upperCategory") String upperCategory, @Param("lowerCategory") String lowerCategory, @Param("adAccount") String adAccount, @Param("nameOfMember") String nameOfMember);

	List<MemberModel> selectConditionedMembersServing(String condition);

	List<MemberModel> selectConditionedMembersRetired(String condition);

}
