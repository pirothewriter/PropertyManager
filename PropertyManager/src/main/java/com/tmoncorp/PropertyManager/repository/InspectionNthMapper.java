package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import com.tmoncorp.PropertyManager.model.InspectionNthModel;

/**
 * 
 * @author pirothewriter
 *
 */

public interface InspectionNthMapper {
	public InspectionNthModel selectLastestNth();

	public int insertNewNth(int nextNth);
	
	public List<InspectionNthModel> selectAllNth();
	
	public int endNth(int nth);

}
