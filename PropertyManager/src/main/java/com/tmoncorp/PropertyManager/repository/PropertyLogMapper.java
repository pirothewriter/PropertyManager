package com.tmoncorp.PropertyManager.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmoncorp.PropertyManager.model.PropertyLogModel;

/**
 * 
 * @author piro
 *
 */

public interface PropertyLogMapper {
	List<PropertyLogModel> selectPropertyLog(String propertyNumber);

	Date selectPropertyNow(@Param("propertyNumber") String propertyNumber, @Param("memberId") String memberId);
}
