package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmoncorp.PropertyManager.model.InspectionLogModel;

/**
 * 
 * @author pirothewriter
 *
 */

public interface InspectionLogMapper {
	public int insertLog(InspectionLogModel inspectionLogModel);

	public List<InspectionLogModel> selectInspectedDataOnMember(@Param("nth") int nth, @Param("adAccount") String adAccount);

	public int deleteInspectedDataOnMember(@Param("nth") int nth, @Param("adAccount") String adAccount);

	public String selectInspedtedDataOfProperty(@Param("nth") int nth, @Param("propertyNumber") String propertyNumber);
}
