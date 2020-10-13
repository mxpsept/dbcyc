package com.kcm.common.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:BasePublicModel</p>
 * <p>Description: 基础公共实体类字段 </p>
 * <p>Company:www.t-petro.com</p> 
 * @author lyj
 * @date 下午3:49:27
 */
@Data
public class BasePublicModel implements Serializable{

	/**
	 * 实现实体类序列化
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 创建人ID
	 */
	private String createUserId;
	/**
	 * 创建时间
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
	private Date createTime;
	/**
	 * 创建单位ID
	 */
	private String createDeptId;

	/**
	 * 修改人ID
	 */
	private String editUserId;

	/**
	 * 修改时间
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
	private Date editTime;

	/**
	 * 修改单位ID
	 */
	private String editDeptId;

}
