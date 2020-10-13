package com.kcm.common.other;

import java.util.UUID;

/**
 * 生成32位的唯一表示
 * 
 * @author 胡波
 */
public class UUIDGenerate {
	public static String getUniqueUuserId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
