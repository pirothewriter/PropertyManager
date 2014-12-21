package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import com.tmoncorp.PropertyManager.model.EquipmentModel;

/**
 * 
 * @author piro
 *
 */

public interface EquipmentMapper {
	public int insertEquipmentInfomation(EquipmentModel equipmentModel);

	public List<EquipmentModel> selectPropertyOnMember(String memberId);

}
