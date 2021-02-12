package com.works.mvcproject.utils;

public class Util {
	
	public String password(String pass, int count)
	{
		String c_pass = pass;
		for (int i = 0; i < count; i++)
		{
			c_pass = MD5(c_pass);
		}		
		return c_pass;
	}

	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

}