package com.tmoncorp.PropertyManager.repository;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tmoncorp.PropertyManager.model.EquipmentModel;
/**
 * 
 * @author piro
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext*.xml" })
public class EquipmentRepositoryTest {
	@Autowired
	private SqlSession sqlSession;
	private EquipmentMapper equipmentMapper;
	@Before
	public void setup(){
		equipmentMapper = sqlSession.getMapper(EquipmentMapper.class);
	}
	
	@Transactional
	@Test
	public void 장비정보_삽입_테스트(){
		equipmentMapper.insertEquipmentInfomation(getTestInsertionData());
	}
	
	@Test
	public void 특정사원의_자산정보를_들고오는지_테스트(){
		List<EquipmentModel> result = equipmentMapper.selectPropertyOnMember("201404016");
		assertNotNull(result);
	}
	
	private EquipmentModel getTestInsertionData(){
		EquipmentModel equipmentModel = new EquipmentModel();
		
		equipmentModel.setPropertyNumber("test");
		equipmentModel.setIncommingFinance(new Date(System.currentTimeMillis()));
		equipmentModel.setIncommingItUnit(new Date(System.currentTimeMillis()));
		equipmentModel.setInfomation1("test");
		equipmentModel.setInfomation2("test");
		equipmentModel.setName("test");
		equipmentModel.setPrice(1000);
		equipmentModel.setProductor("test");
		equipmentModel.setSeller("test");
		equipmentModel.setUser("티켓몬스터");
		equipmentModel.setLowerCategory("test");
		equipmentModel.setUpperCategory("test");
		return equipmentModel;
	}

}
