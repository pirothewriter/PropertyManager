package com.tmoncorp.PropertyManager.service;

import org.junit.Before;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.tmoncorp.PropertyManager.model.EquipmentModel;
import com.tmoncorp.PropertyManager.repository.EquipmentRepository;

public class EquipmentServiceTest {
	private EquipmentService equipmentService;
	
	@Before
	public void setup(){
		equipmentService = new EquipmentService();
	}
	
	@Test
	public void 데이터_인서트테스트(){
		EquipmentRepository mockedRepository = mock(EquipmentRepository.class);
	}
}
