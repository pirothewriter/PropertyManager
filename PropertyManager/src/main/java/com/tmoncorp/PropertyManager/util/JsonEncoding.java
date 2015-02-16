package com.tmoncorp.PropertyManager.util;

import java.util.List;

import com.google.gson.Gson;
import com.tmoncorp.PropertyManager.model.EquipmentModel;

/**
 * 
 * @author piro
 *
 */

public class JsonEncoding {
	public String encodingJson(List<?> arrayList) {
		Gson gson = new Gson();

		return gson.toJson(arrayList);
	}

	public String encodingJson(EquipmentModel equipmentModel) {
		Gson gson = new Gson();

		return gson.toJson(equipmentModel);
	}

}
