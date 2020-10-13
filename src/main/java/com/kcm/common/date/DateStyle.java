package com.kcm.common.date;


/**
 * @author xxx
 */
public enum DateStyle {

	/**
	 * YYYY_MM_D
	 */
	YYYY_MM_D("yyyy-MM-d"),
	/**
	 * YYYY_M_DD
	 */
	YYYY_M_DD("yyyy-M-dd"),
	/**
	 * YYYY_M_D
	 */
	YYYY_M_D("yyyy-M-d"),
	 /*MM_DD("MM-dd"),  */
	    /*YYYY_MM("yyyy-MM"),  */
	/**
	 * Y_M_D
	 */
	Y_M_D("y-M-d"),
	/**
	 * YYYY_MM_DD
	 */
	YYYY_MM_DD("yyyy-MM-dd"),
	/* MM_DD_HH_MM("MM-dd HH:mm"),
	    MM_DD_HH_MM_SS("MM-dd HH:mm:ss"), */
	/**
	 * YYYY_MM_DD_HH_MM
	 */
	YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
	/**
	 * YYYY_MM_DD_HH_MM_SS
	 */
	YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
	/**
	 * Y_M_D_H_M_S
	 */
	Y_M_D_H_M_S("y-M-d HH:mm:ss"),
/*	    MM_DD_EN("MM/dd"),*/  
	   /* YYYY_MM_EN("yyyy/MM"),*/
	/**
	 * YYYY_MM_DD_EN
	 */
	YYYY_MM_DD_EN("yyyy/MM/dd"),
	/**
	 * YYYY_M_DD_EN
	 */
	YYYY_M_DD_EN("yyyy/M/dd"),
	/**
	 * YYYY_M_D_EN
	 */
	YYYY_M_D_EN("yyyy/M/d"),
	/**
	 * YYYY_MM_D_EN
	 */
	YYYY_MM_D_EN("yyyy/MM/d"),
	   /* MM_DD_HH_MM_EN("MM/dd HH:mm"),  
	    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss"),*/
	/**
	 * YYYY_MM_DD_HH_MM_EN
	 */
	YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm"),
	/**
	 * YYYY_MM_DD_HH_MM_SS_EN
	 */
	YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss"),
	/**
	 * Y_M_D_H_M_S_EN
	 */
	Y_M_D_H_M_S_EN("y/M/d HH:mm:ss"),
	/*MM_DD_CN("MM月dd日"),
	    YYYY_MM_CN("yyyy年MM月"),*/
	/**
	 * YYYY_MM_DD_CN
	 */
	YYYY_MM_DD_CN("yyyy年MM月dd日"),

	    /*MM_DD_HH_MM_CN("MM月dd日 HH:mm"),  
	    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss"),  */
	/**
	 * YYYY_MM_DD_HH_MM_CN
	 */
	YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm"),
	/**
	 * YYYY_MM_DD_HH_MM_SS_CN
	 */
	YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss"),
	      
	    /*HH_MM("HH:mm"), */
	/**
	 * HH_MM_SS
	 */
	HH_MM_SS("HH:mm:ss");
	      
	      
	private String value;
	      
	DateStyle(String value) {
	    this.value = value;
	}
	      
	public String getValue() {
	    return value;
	}
}