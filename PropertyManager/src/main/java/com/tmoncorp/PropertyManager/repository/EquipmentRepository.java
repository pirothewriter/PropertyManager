package com.tmoncorp.PropertyManager.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmoncorp.PropertyManager.model.EquipmentModel;

@Repository
public class EquipmentRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public void insertEquipmentInfomation(EquipmentModel equipmentModel){
		EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
		equipmentMapper.insertEquipmentInfomation(equipmentModel);
	}
}
