package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import com.tmoncorp.PropertyManager.model.MemberModel;

/**
 * 
 * @author piro
 *
 */

public interface MemberMapper {
	int insertMemberInfomation(MemberModel memberModel);
	List<MemberModel> selectAllMembers();

}
