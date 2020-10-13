package com.kcm.common.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期实现类(实现 String 转Date 和Date转String)
 */
public class DateUtils {
   public static Date stringToDate(String str){
       //日期格式
	   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       Date date = null;   
        try {
			date = format.parse(str);
		} catch (ParseException e) {			
			e.printStackTrace();
		}
       
       return date;
   }
   
   public static String dateToStr(Date date){
       //日期格式
	   SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");
       String tiem = sformat.format(date);
       return tiem;
   }
   
   /**
    * 获取两个日期相差的月数，d2>d1
    */
   public static int getMonthDiff(Date d1, Date d2) {
       Calendar c1 = Calendar.getInstance();
       Calendar c2 = Calendar.getInstance();
       c1.setTime(d1);
       c2.setTime(d2);
       int year1 = c1.get(Calendar.YEAR);
       int year2 = c2.get(Calendar.YEAR);
       int month1 = c1.get(Calendar.MONTH);
       int month2 = c2.get(Calendar.MONTH);
       int day1 = c1.get(Calendar.DAY_OF_MONTH);
       int day2 = c2.get(Calendar.DAY_OF_MONTH);
       // 获取年的差值 
       int yearInterval = year1 - year2;
       // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
       boolean b = (month1 < month2 || month1 == month2 && day1 < day2);
       if (b) {
           yearInterval--;
       }
       // 获取月数差值
       int monthInterval = (month1 + 12) - month2;
       if (day1 < day2) {
           monthInterval--;
       }
       monthInterval %= 12;
       int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);
       return monthsDiff;
   }
}
