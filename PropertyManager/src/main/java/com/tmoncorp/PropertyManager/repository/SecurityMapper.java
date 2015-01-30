package com.tmoncorp.PropertyManager.repository;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author piro
 *
 */

public interface SecurityMapper {
	public int insertUser(@Param("userName") String userName, @Param("password") String password);

}
