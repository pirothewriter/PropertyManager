package com.tmoncorp.PropertyManager.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 
 * @author piro
 *
 */

public class ExchangeCharacterSet {
	public String convert(String str, String encoding) throws IOException {
		ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
		requestOutputStream.write(str.getBytes(encoding));
		return requestOutputStream.toString(encoding);
	}
}
