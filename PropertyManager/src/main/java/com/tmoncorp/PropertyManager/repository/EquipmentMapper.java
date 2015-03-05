package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmoncorp.PropertyManager.model.EquipmentModel;

/**
 * 
 * @author piro
 *
 */

public interface EquipmentMapper {
	public int insertEquipmentInfomation(EquipmentModel equipmentModel);

	public List<EquipmentModel> selectPropertyOnMember(String adAccount);

	public List<EquipmentModel> selectOwnerlessEquipments(@Param("nowPage") int nowPage, @Param("viewSolePage") int viewSolePage, @Param("upperCategory") String upperCategory, @Param("lowerCategory") String lowerCategory, @Param("propertyNumber") String propertyNumber);

	public int selectCountAllOwnerlessEquipment(@Param("upperCategory") String upperCategory, @Param("lowerCategory") String lowerCategory, @Param("propertyNumber") String propertyNumber);

	public List<EquipmentModel> selectAllEquipment(@Param("nowPage") int nowPage, @Param("viewSolePage") int viewSolePage, @Param("upperCategory") String upperCategory, @Param("lowerCategory") String lowerCategory, @Param("propertyNumber") String propertyNumber);
	
	public int selectCountAllEquipment(@Param("upperCategory") String upperCategory, @Param("lowerCategory") String lowerCategory, @Param("propertyNumber") String propertyNumber);

	public EquipmentModel getPropertyInfomation(String propertyNumber);

}
