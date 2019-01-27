package com.pahlsoft.aws;

import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class defines common routines for generating authentication signatures
 * for AWS Platform requests.
 */
public class SignatureMaker {
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	
	public static void main(String [] args) throws SignatureException {
		System.out.println("Converted String is: " + calculateRFC2104HMAC(args[0],args[1]) );
	}
	
	public static String calculateRFC2104HMAC(String data, String key) throws SignatureException {
		String result;
		
		try {

			// get an hmac_sha1 key from the raw key bytes
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(),
					HMAC_SHA1_ALGORITHM);

			// get an hmac_sha1 Mac instance and initialize with the signing key
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);

			// compute the hmac on input data bytes
			byte[] rawHmac = mac.doFinal(data.getBytes());

			// base64-encode the hmac
			result = Encoder.EncodeBase64(rawHmac);

		} catch (Exception e) {
			throw new SignatureException("Failed to generate HMAC : "
					+ e.getMessage());
		}
		return result;
	}
}