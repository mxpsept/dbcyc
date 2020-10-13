/**
 * @Project		train-controller
 * @File		Flag.java
 * @Package		com.kcm.train.commons.util.enumeration
 * @Version		V1.0
 * @Date		2016年6月28日 上午10:57:45
 * @Author		胡波
 * Copyright (c) All Rights Reserved, 2016.
 */

package com.kcm.common.other;

/**
 * @Description 接口返回标识定义
 * @ClassName Flag
 * @Date 2016年6月28日 上午10:57:45
 * @Author 胡波
 * @Copyright (c) All Rights Reserved, 2016.
 */
public enum Flag {

	/**
	 * 成功返回0
	 */
	SUCCESS("成功", "0"),
	/**
	 * 失败返回1
	 */
	ERROR("失败", "1"),
	/**
	 * 异常返回2
	 */
	EXCEPTION("异常", "2"),
	/**
	 * 验证失败返回3
	 */
	VALITION("验证失败", "3"),
	/**
	 * 登录失效返回4
	 */
	INVALID("登陆失效", "4");

	/**
	 * 成员变量
	 */
	private String name;
	/**
	 * 索引
	 */
	private String index;

	/**
	 * 构造方法
	 *
	 * @param name 成员变量
	 * @param index 索引
	 */
	private Flag(String name, String index) {
		this.name = name;
		this.index = index;
	}

	/**
	 * 通过索引获得成员变量
	 *
	 * @param index 索引
	 * @return 成员变量
	 */
	public static String getName(String index) {
		for (Flag c : Flag.values()) {
			if (index.equals(c.getIndex())) {
				return c.name;
			}
		}
		return null;
	}

	/**
	 * 通过成员变量获得下表
	 *
	 * @param name 成员变量
	 * @return 下表
	 */
	public static String getIndex(String name) {
		for (Flag c : Flag.values()) {
			if (c.getName() == name) {
				return c.index;
			}
		}
		return "";
	}

	/**
	 * getter和setter方法
	 * @return 结果
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
}
