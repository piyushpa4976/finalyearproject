package com.LM.api.utilityclasses;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Security {
	
	public String md5(String string) {

		String md5value = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytesOfMessage = string.getBytes("UTF-8");
			byte[] thedigest = md.digest(bytesOfMessage);

			md5value = Hex.encodeHexString(thedigest);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return md5value;
	}

}
