package com.test.util;

import java.math.BigInteger;
import java.security.MessageDigest;

//MD5加密工具类
public class MD5Tools {
	public static String Md5Data(String str) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(str.getBytes());
		//八位二进制
		return new BigInteger(1,md5.digest()).toString(16);
	}
}
