package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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

	public List<EquipmentModel> selectPropertyOnMember(String adAccount) {
		EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
		return equipmentMapper.selectPropertyOnMember(adAccount);
	}

	public List<EquipmentModel> selectOwnerlessEquipments(int nowPage, int viewSolePage, String upperCategory, String lowerCategory, String propertyNumber) {
		EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
		return equipmentMapper.selectOwnerlessEquipments(nowPage, viewSolePage, upperCategory, lowerCategory, propertyNumber);
	}

	public int selectAllOwnerlessEquipment(String upperCategory, String lowerCategory, String propertyNumber) {
		EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
		return equipmentMapper.selectCountAllOwnerlessEquipment(upperCategory, lowerCategory, propertyNumber);
	}

	public List<EquipmentModel> selectAllEquipment(int nowPage, int viewSolePage, String upperCategory, String lowerCategory, String propertyNumber) {
		EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
		return equipmentMapper.selectAllEquipment(nowPage, viewSolePage, upperCategory, lowerCategory, propertyNumber);
	}

	public int selectAllEquipment(String upperCategory, String lowerCategory, String propertyNumber) {
		EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
		return equipmentMapper.selectCountAllEquipment(upperCategory, lowerCategory, propertyNumber);
	}

}
