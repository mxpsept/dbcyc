package com.kcm.common.date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author xxx
 */
public class DateList {

	/**
	 * @Description 获取周信息
	 * @param @return 参数
	 * @return List<String> 返回类型
	 * @throws
	 */
	public List<String> weekList() {
		List<String> returnList = new ArrayList<String>();
		returnList.add("星期一");
		returnList.add("星期二");
		returnList.add("星期三");
		returnList.add("星期四");
		returnList.add("星期五");
		returnList.add("星期六");
		returnList.add("星期日");
		return returnList;
	}

	/**
	 * @Description 获取某月的天数信息
	 * @param @return 参数
	 * @return List<String> 返回类型
	 * @throws
	 */
	public List<String> monthOfdayList(int num) {
		List<String> returnList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MONTH, num - 1);
		for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			returnList.add(i + 1 + "日");
		}
		return returnList;
	}

	/**
	 * @Description 获取年的月份信息
	 * @param @return 参数
	 * @return List<String> 返回类型
	 * @throws
	 */
	public List<String> yeahOfMonthList() {
		List<String> returnList = new ArrayList<String>();
		int monthNumber =12;
		for (int i = 0; i < monthNumber; i++) {
			returnList.add(i + 1 + "月");
		}
		return returnList;
	}
}
