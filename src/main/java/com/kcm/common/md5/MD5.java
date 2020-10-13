package com.kcm.common.md5;

import java.security.MessageDigest;

/**
 * @file: MD5.java
 * @description：md5 类实现了RSA Data Security, Inc.在提交给IETF 的RFC1321中的MD5
 *                  message-digest 算法。
 * @version： 1.0
 */
public class MD5 {
	private final static String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0){
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return HEX_DIGITS[d1] + HEX_DIGITS[d2];
	}

	/**
	 * MD5加密，将一字符串加密成16进制字串
	 * 
	 * @param origin
	 *            元字符串
	 * @return 加密结果
	 */
	public static String md5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}

	/**
	 * 生成随机的12位字母数字大小写密码
	 * @return
	 */
	public static String randomPassword() {
		StringBuilder sb=new StringBuilder();

        int i1 = 4;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < i1; j++) {
				double rand = Math.random();
				sb.append((char) (rand * ('9' - '0') + '0'));
				sb.append((char) (rand * ('Z' - 'A') + 'A'));
				sb.append((char) (rand * ('z' - 'a') + 'a'));
			}
		}
		return sb.toString();
	}


	public static void main(String[] args) {
		System.err.println(md5Encode("11111111"));
	}

}
