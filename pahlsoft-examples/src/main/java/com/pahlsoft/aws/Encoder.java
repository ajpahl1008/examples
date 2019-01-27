package com.pahlsoft.aws;

import org.apache.axis.encoding.Base64;


public class Encoder {

	public static String EncodeBase64(byte[] rawData) {
		return Base64.encode(rawData);
	}
}
