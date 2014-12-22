package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmoncorp.PropertyManager.model.EquipmentModel;

/**
 * 
 * @author piro
 *
 */

@Repository
public class EquipmentRepository {
	@Autowired
	private SqlSession sqlSession;

	public int insertEquipmentInfomation(EquipmentModel equipmentModel) {
		EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
		int result = equipmentMapper.insertEquipmentInfomation(equipmentModel);
		return result;
	}

	public List<EquipmentModel> selectPropertyOnMember(String memberId) {
		EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
		return equipmentMapper.selectPropertyOnMember(memberId);
	}

	public List<EquipmentModel> selectOwnerlessEquipments() {
		EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
		return equipmentMapper.selectOwnerlessEquipments();
	}
}
