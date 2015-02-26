package com.tmoncorp.PropertyManager.repository;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author piro
 *
 */

public interface SecurityMapper {
	public int insertUser(@Param("username") String userName);

	public int insertAuthority(@Param("username") String userName);

	public String getPassword(String username);

	public String getRole(String username);

	public int grantAdmin(String adAccount);

	public int revokeAdmin(String adAccount);

	public int revokeUser(String adAccount);

	public int changePassword(@Param("adAccount") String adAccount, @Param("password") String password);

}
