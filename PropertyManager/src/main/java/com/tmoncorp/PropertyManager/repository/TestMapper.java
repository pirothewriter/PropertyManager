package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import com.tmoncorp.PropertyManager.model.TestModel;

public interface TestMapper {
	List<TestModel> selectTest();
}
