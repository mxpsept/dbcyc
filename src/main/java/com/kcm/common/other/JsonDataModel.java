package com.kcm.common.other;

import java.io.Serializable;

/**
 * 通用返回json模型
 *
 * @author shawn
 * @date 17/10/2018 11:46
 * @version 1.0
 */
public class JsonDataModel implements Serializable {
    private static final long serialVersionUID = -8016457922614492926L;

	/**
	 * 返回代码,一般0成功,1失败
	 */
	private Integer code;

	/**
	 * 返回数据,一般成功才返回
	 */
    private Object data;

	/**
	 * 返回信息,一般失败返回失败信息
	 */
	private String message;

	/**
	 * 返回状态代码,一般用于说明失败代码
	 */
    private Integer status;

	public JsonDataModel(Integer code) {
        this.code = code;
    }

	public JsonDataModel(Object data) {
        this.data = data;
    }

	public JsonDataModel(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

	public JsonDataModel(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

	public JsonDataModel(Integer code, Object data, String message, Integer status) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.status = status;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public JsonDataModel() {
	}
}
