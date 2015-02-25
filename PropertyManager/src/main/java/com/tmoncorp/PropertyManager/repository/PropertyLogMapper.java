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

	Date selectPropertyNow(@Param("propertyNumber") String propertyNumber, @Param("adAccount") String adAccount);

	int insertUrgentProperty(@Param("adAccount") String adAccount, @Param("propertyNumber") String propertyNumber);

	int insertUrgentPropertyLog(@Param("adAccount") String adAccount, @Param("propertyNumber") String propertyNumber);

	int deleteReleaseProperty(@Param("adAccount") String adAccount, @Param("propertyNumber") String propertyNumber);

	int updateReleaseLogProperty(@Param("adAccount") String adAccount, @Param("propertyNumber") String propertyNumber);

	int releaseAllEquipmentOnRetireMember(String adAccount);

	int logWithdrawDateOfRetireMembersEquipment(String adAccount);

	List<PropertyLogModel> selectPersonalLog(String adAccount);

	List<PropertyLogModel> getPropertyInfomation(String propertyNumber);
}
