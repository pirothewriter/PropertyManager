package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmoncorp.PropertyManager.model.InspectionModel;

/**
 * 
 * @author pirothewriter
 *
 */

public interface InspectionMapper {
	public List<InspectionModel> selectInspectedData(@Param("page") int page, @Param("viewSolePage") int viewSolePage, @Param("nth") int nth, @Param("adAccount") String adAccount, @Param("memberName") String memberName, @Param("flagDifference") char flagDifference);

	public int insertInspection(InspectionModel inspectionModel);

	public int deleteInspectedData(@Param("nth") int nth, @Param("adAccount") String adAccount, @Param("propertyNumber") String propertyNumber);

	public int getMaximumPage(@Param("nth") int nth, @Param("adAccount") String adAccount, @Param("memberName") String memberName, @Param("flagDifference") char flagDifference);

	public InspectionModel getThisNthPropertyInspectedData(@Param("nth") int nth, @Param("propertyNumber") String propertyNumber);
}
