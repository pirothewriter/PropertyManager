package com.tmoncorp.PropertyManager.util;

import java.util.List;

import com.google.gson.Gson;

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

}
