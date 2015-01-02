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

	public List<EquipmentModel> selectPropertyOnMember(String memberId);

	public List<EquipmentModel> selectOwnerlessEquipments(@Param("nowPage") int nowPage, @Param("viewSolePage") int viewSolePage);

	public int selectCountAllOwnerlessEquipment();

}
