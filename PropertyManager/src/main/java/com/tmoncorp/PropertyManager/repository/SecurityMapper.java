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

}
