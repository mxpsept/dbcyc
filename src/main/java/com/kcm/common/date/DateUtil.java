package com.kcm.common.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xxx
 * <p>日期转换格式的工具包</p>
 * <p>Date、DateTime、TimeStamp、DateFormat</p>	
 * <p>User: Zhugong Team
 * <p>Date: 2016年12月20日 下午8:08:58
 * <p>Version: 1.0
 */
public class DateUtil {


	/**
     * 获取SimpleDateFormat 
     * @param parttern 日期格式 
     * @return SimpleDateFormat对象 
     * @throws RuntimeException 异常：非法日期格式 
     */  
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {  
        return new SimpleDateFormat(parttern);  
    }  
  
    /** 
     * 获取日期中的某数值。如获取月份 
     * @param date 日期 
     * @param dateType 日期格式 
     * @return 数值 
     */  
    private static int getInteger(Date date, int dateType) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        return calendar.get(dateType);  
    }  
      
    /** 
     * 增加日期中某类型的某数值。如增加日期 
     * @param date 日期字符串 
     * @param dateType 类型 
     * @param amount 数值 
     * @return 计算后日期字符串 
     */  
    private static String addInteger(String date, int dateType, int amount) {  
        String dateString = null;  
        DateStyle dateStyle = getDateStyle(date);  
        if (dateStyle != null) {  
            Date myDate = stringToDate(date, dateStyle);  
            myDate = addInteger(myDate, dateType, amount);  
            dateString = dateToString(myDate, dateStyle);  
        }  
        return dateString;  
    }  
      
    /** 
     * 增加日期中某类型的某数值。如增加日期 
     * @param date 日期 
     * @param dateType 类型 
     * @param amount 数值 
     * @return 计算后日期 
     */  
    private static Date addInteger(Date date, int dateType, int amount) {  
        Date myDate = null;  
        if (date != null) {  
            Calendar calendar = Calendar.getInstance();  
            calendar.setTime(date);  
            calendar.add(dateType, amount);  
            myDate = calendar.getTime();  
        }  
        return myDate;  
    }  
  
    /** 
     * 获取精确的日期 
     * @param timestamps 时间long集合 
     * @return 日期 
     */  
    private static Date getAccurateDate(List<Long> timestamps) {  
        Date date = null;  
        long timestamp = 0;
        int length = (int )(timestamps.size()/0.75+1);
        Map<Long, long[]> map = new HashMap<Long, long[]>(length);
        List<Long> absoluteValues = new ArrayList<Long>(length);
  
        if (timestamps != null && timestamps.size() > 0) {  
            if (timestamps.size() > 1) {  
                for (int i = 0; i < timestamps.size(); i++) {  
                    for (int j = i + 1; j < timestamps.size(); j++) {  
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));  
                        absoluteValues.add(absoluteValue);  
                        long[] timestampTmp = { timestamps.get(i), timestamps.get(j) };  
                        map.put(absoluteValue, timestampTmp);  
                    }  
                }  
  
                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的  
                long minAbsoluteValue = -1;  
                if (!absoluteValues.isEmpty()) {  
                    // 如果timestamps的size为2，这是差值只有一个，因此要给默认值  
                    minAbsoluteValue = absoluteValues.get(0);  
                }  
                for (int i = 0; i < absoluteValues.size(); i++) {  
                    for (int j = i + 1; j < absoluteValues.size(); j++) {  
                        if (absoluteValues.get(i) > absoluteValues.get(j)) {  
                            minAbsoluteValue = absoluteValues.get(j);  
                        } else {  
                            minAbsoluteValue = absoluteValues.get(i);  
                        }  
                    }  
                }  
  
                if (minAbsoluteValue != -1) {  
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);  
                    if (absoluteValues.size() > 1) {  
                        timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);  
                    } else if (absoluteValues.size() == 1) {  
                        // 当timestamps的size为2，需要与当前时间作为参照  
                        long dateOne = timestampsLastTmp[0];  
                        long dateTwo = timestampsLastTmp[1];
                        long dateDifference = 100000000000L;
                        if ((Math.abs(dateOne - dateTwo)) <dateDifference ) {
                            timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);  
                        } else {  
                            long now = System.currentTimeMillis();
                            if (Math.abs(dateOne - now) <= Math.abs(dateTwo - now)) {  
                                timestamp = dateOne;  
                            } else {  
                                timestamp = dateTwo;  
                            }  
                        }  
                    }  
                }  
            } else {  
                timestamp = timestamps.get(0);  
            }  
        }  
  
        if (timestamp != 0) {  
            date = new Date(timestamp);  
        }  
        return date;  
    }  
  
    /** 
     * 判断字符串是否为日期字符串 
     * @param date 日期字符串 
     * @return true or false 
     */  
    public static boolean isDate(String date) {  
        boolean isDate = false;  
        //判断日期字符串不包含中文、英文字母
        String regex=".*[a-zA-Z\u4e00-\u9fa5]+.*";  
        Matcher m=Pattern.compile(regex).matcher(date);  
        if (date != null) {  
            if (stringToDate(date) != null) { 
            	if(!m.matches()){
            		isDate = true;  
            	}
            }  
        }  
        return isDate;  
    }  
    
   /* *//**
	 * 日期校验
	 * @param str
	 * @return
	 *//*
	public static boolean isValidDate(String str) {
	      boolean convertSuccess=true;
	    // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	       
	       try {
	          format.setLenient(false);
	          System.out.println(format1.parse(str));
	          format.parse(str);
	       } catch (Exception e) {
	           convertSuccess=false;
	       } 
	       return convertSuccess;
	}*/
    
    
    
    /** 
     * 获取日期字符串的日期风格。失敗返回null。 
     * @param date 日期字符串 
     * @return 日期风格 
     */  
    public static DateStyle getDateStyle(String date) {  
        DateStyle dateStyle = null;  
        Map<Long, DateStyle> map = new HashMap<Long, DateStyle>(16);
        List<Long> timestamps = new ArrayList<Long>();  
        for (DateStyle style : DateStyle.values()) {  
            Date dateTmp = stringToDate(date, style.getValue());  
            if (dateTmp != null) {  
                timestamps.add(dateTmp.getTime());  
                map.put(dateTmp.getTime(), style);  
            }  
        }  
        dateStyle = map.get(getAccurateDate(timestamps).getTime());  
        return dateStyle;  
    }  
  
    /** 
     * 将日期字符串转化为日期。失败返回null。 
     * @param date 日期字符串 
     * @return 日期 
     */  
    public static Date stringToDate(String date) {  
        DateStyle dateStyle = null;  
        return stringToDate(date, dateStyle);  
    }  
  
    /** 
     * 将日期字符串转化为日期。失败返回null。 
     * @param date 日期字符串 
     * @param parttern 日期格式 
     * @return 日期 
     */  
    public static Date stringToDate(String date, String parttern) {  
        Date myDate = null;  
        DateFormat df= null;
        if (date != null) {  
            try {  
               	 df= new SimpleDateFormat(parttern);
               	 //指定日期时间解析是否不严格，默认是不严格(true)
               	 df.setLenient(false);
               	 //转换日期格式，从而校验
               	  myDate=df.parse(date);
               	  //若不出现异常则为正常数据
               	  return myDate; 
            } catch (Exception e) { 
            	myDate=null;
            	return myDate;  
            }  
        }else{
        	return null;  
        }
    }  
    
    /** 
     * 将日期字符串转化为日期。失败返回null。 (批量导入校验)
     * @param date 日期字符串 
     * @param parttern 日期格式 
     * @return 日期 
     */  
    public static Date stringToDate1(String date, String parttern) {  
        Date myDate = null;  
        DateFormat df= null;
        if (date != null) {  
            try {  
               	 df= new SimpleDateFormat(parttern);
               	 //指定日期时间解析是否不严格，默认是不严格(true)
               	 df.setLenient(false);
               	 //转换日期格式，从而校验
               	  myDate=df.parse(date);
               	 if(myDate!=null){
             		 SimpleDateFormat sdf=new SimpleDateFormat(parttern);
                      String str=sdf.format(myDate);

                      if(!date.equals(str)){
                     	 myDate=null;
                      }
                 }
               	  //若不出现异常则为正常数据
               	  return myDate; 
            } catch (Exception e) { 
            	myDate=null;
            	return myDate;  
            }  
        }else{
        	return null;  
        }
    }  
  
    /** 
     * 将日期字符串转化为日期。失败返回null。 
     * @param date 日期字符串 
     * @param dateStyle 日期风格 
     * @return 日期 
     */  
    public static Date stringToDate(String date, DateStyle dateStyle) {  
        Date myDate = null;  
        if (dateStyle == null) {  
            List<Long> timestamps = new ArrayList<Long>();  
            for (DateStyle style : DateStyle.values()) {  
                Date dateTmp = stringToDate1(date, style.getValue());  
                if (dateTmp != null) {  
                    timestamps.add(dateTmp.getTime());  
                }  
            }  
            myDate = getAccurateDate(timestamps);  
        } else {  
            myDate = stringToDate(date, dateStyle.getValue()); 
        }  
        return myDate;  
    }  
    
    
  
    /** 
     * 将日期转化为日期字符串。失败返回null。 
     * @param date 日期 
     * @param parttern 日期格式 
     * @return 日期字符串 
     */  
    public static String dateToString(Date date, String parttern) {  
        String dateString = null;  
        if (date != null) {  
            try {  
                dateString = getDateFormat(parttern).format(date);  
            } catch (Exception e) {  
            }  
        }  
        return dateString;  
    }  
  
    /** 
     * 将日期转化为日期字符串。失败返回null。 
     * @param date 日期 
     * @param dateStyle 日期风格 
     * @return 日期字符串 
     */  
    public static String dateToString(Date date, DateStyle dateStyle) {  
        String dateString = null;  
        if (dateStyle != null) {  
            dateString = dateToString(date, dateStyle.getValue());  
        }  
        return dateString;  
    }  
  
    /** 
     * 将日期字符串转化为另一日期字符串。失败返回null。 
     * @param date 旧日期字符串 
     * @param parttern 新日期格式 
     * @return 新日期字符串 
     */  
    public static String stringToString(String date, String parttern) {  
        return stringToString(date, null, parttern);  
    }  
  
    /** 
     * 将日期字符串转化为另一日期字符串。失败返回null。 
     * @param date 旧日期字符串 
     * @param dateStyle 新日期风格 
     * @return 新日期字符串 
     */  
    public static String stringToString(String date, DateStyle dateStyle) {  
        return stringToString(date, null, dateStyle);  
    }  
  
    /** 
     * 将日期字符串转化为另一日期字符串。失败返回null。 
     * @param date 旧日期字符串 
     * @param olddParttern 旧日期格式 
     * @param newParttern 新日期格式 
     * @return 新日期字符串 
     */  
    public static String stringToString(String date, String olddParttern, String newParttern) {  
        String dateString = null;  
        if (olddParttern == null) {  
            DateStyle style = getDateStyle(date);  
            if (style != null) {  
                Date myDate = stringToDate(date, style.getValue());  
                dateString = dateToString(myDate, newParttern);  
            }  
        } else {  
            Date myDate = stringToDate(date, olddParttern);  
            dateString = dateToString(myDate, newParttern);  
        }  
        return dateString;  
    }  
  
    /** 
     * 将日期字符串转化为另一日期字符串。失败返回null。 
     * @param date 旧日期字符串 
     * @param olddDteStyle 旧日期风格 
     * @param newDateStyle 新日期风格 
     * @return 新日期字符串 
     */  
    public static String stringToString(String date, DateStyle olddDteStyle, DateStyle newDateStyle) {  
        String dateString = null;  
        if (olddDteStyle == null) {  
            DateStyle style = getDateStyle(date);  
            dateString = stringToString(date, style.getValue(), newDateStyle.getValue());  
        } else {  
            dateString = stringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());  
        }  
        return dateString;  
    }  
  
    /** 
     * 增加日期的年份。失败返回null。 
     * @param date 日期 
     * @param yearAmount 增加数量。可为负数 
     * @return 增加年份后的日期字符串 
     */  
    public static String addYear(String date, int yearAmount) {  
        return addInteger(date, Calendar.YEAR, yearAmount);  
    }  
      
    /** 
     * 增加日期的年份。失败返回null。 
     * @param date 日期 
     * @param yearAmount 增加数量。可为负数 
     * @return 增加年份后的日期 
     */  
    public static Date addYear(Date date, int yearAmount) {  
        return addInteger(date, Calendar.YEAR, yearAmount);  
    }  
      
    /** 
     * 增加日期的月份。失败返回null。 
     * @param date 日期 
     * @param yearAmount 增加数量。可为负数 
     * @return 增加月份后的日期字符串 
     */  
    public static String addMonth(String date, int yearAmount) {  
        return addInteger(date, Calendar.MONTH, yearAmount);  
    }  
      
    /** 
     * 增加日期的月份。失败返回null。 
     * @param date 日期 
     * @param yearAmount 增加数量。可为负数 
     * @return 增加月份后的日期 
     */  
    public static Date addMonth(Date date, int yearAmount) {  
        return addInteger(date, Calendar.MONTH, yearAmount);  
    }  
      
    /** 
     * 增加日期的天数。失败返回null。 
     * @param date 日期字符串 
     * @param dayAmount 增加数量。可为负数 
     * @return 增加天数后的日期字符串 
     */  
    public static String addDay(String date, int dayAmount) {  
        return addInteger(date, Calendar.DATE, dayAmount);  
    }  
  
    /** 
     * 增加日期的天数。失败返回null。 
     * @param date 日期 
     * @param dayAmount 增加数量。可为负数 
     * @return 增加天数后的日期 
     */  
    public static Date addDay(Date date, int dayAmount) {  
        return addInteger(date, Calendar.DATE, dayAmount);  
    }  
      
    /** 
     * 增加日期的小时。失败返回null。 
     * @param date 日期字符串 
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期字符串 
     */  
    public static String addHour(String date, int hourAmount) {  
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);  
    }  
  
    /** 
     * 增加日期的小时。失败返回null。 
     * @param date 日期 
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期 
     */  
    public static Date addHour(Date date, int hourAmount) {  
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);  
    }  
      
    /** 
     * 增加日期的分钟。失败返回null。 
     * @param date 日期字符串 
     * @param hourAmount 增加数量。可为负数
     * @return 增加分钟后的日期字符串 
     */  
    public static String addMinute(String date, int hourAmount) {  
        return addInteger(date, Calendar.MINUTE, hourAmount);  
    }  
  
    /** 
     * 增加日期的分钟。失败返回null。 
     * @param date 日期 
     * @param hourAmount 增加数量。可为负数
     * @return 增加分钟后的日期 
     */  
    public static Date addMinute(Date date, int hourAmount) {  
        return addInteger(date, Calendar.MINUTE, hourAmount);  
    }  
      
    /** 
     * 增加日期的秒钟。失败返回null。 
     * @param date 日期字符串 
     * @param hourAmount 增加数量。可为负数
     * @return 增加秒钟后的日期字符串 
     */  
    public static String addSecond(String date, int hourAmount) {  
        return addInteger(date, Calendar.SECOND, hourAmount);  
    }  
  
    /** 
     * 增加日期的秒钟。失败返回null。 
     * @param date 日期 
     * @param hourAmount 增加数量。可为负数
     * @return 增加秒钟后的日期 
     */  
    public static Date addSecond(Date date, int hourAmount) {  
        return addInteger(date, Calendar.SECOND, hourAmount);  
    }  
  
    /** 
     * 获取日期的年份。失败返回0。 
     * @param date 日期字符串 
     * @return 年份 
     */  
    public static int getYear(String date) {  
        return getYear(stringToDate(date));  
    }  
  
    /** 
     * 获取日期的年份。失败返回0。 
     * @param date 日期 
     * @return 年份 
     */  
    public static int getYear(Date date) {  
        return getInteger(date, Calendar.YEAR);  
    }  
  
    /** 
     * 获取日期的月份。失败返回0。 
     * @param date 日期字符串 
     * @return 月份 
     */  
    public static int getMonth(String date) {  
        return getMonth(stringToDate(date));  
    }  
  
    /** 
     * 获取日期的月份。失败返回0。 
     * @param date 日期 
     * @return 月份 
     */  
    public static int getMonth(Date date) {  
        return getInteger(date, Calendar.MONTH);  
    }  
  
    /** 
     * 获取日期的天数。失败返回0。 
     * @param date 日期字符串 
     * @return 天 
     */  
    public static int getDay(String date) {  
        return getDay(stringToDate(date));  
    }  
  
    /** 
     * 获取日期的天数。失败返回0。 
     * @param date 日期 
     * @return 天 
     */  
    public static int getDay(Date date) {  
        return getInteger(date, Calendar.DATE);  
    }  
      
    /** 
     * 获取日期的小时。失败返回0。 
     * @param date 日期字符串 
     * @return 小时 
     */  
    public static int getHour(String date) {  
        return getHour(stringToDate(date));  
    }  
  
    /** 
     * 获取日期的小时。失败返回0。 
     * @param date 日期 
     * @return 小时 
     */  
    public static int getHour(Date date) {  
        return getInteger(date, Calendar.HOUR_OF_DAY);  
    }  
      
    /** 
     * 获取日期的分钟。失败返回0。 
     * @param date 日期字符串 
     * @return 分钟 
     */  
    public static int getMinute(String date) {  
        return getMinute(stringToDate(date));  
    }  
  
    /** 
     * 获取日期的分钟。失败返回0。 
     * @param date 日期 
     * @return 分钟 
     */  
    public static int getMinute(Date date) {  
        return getInteger(date, Calendar.MINUTE);  
    }  
      
    /** 
     * 获取日期的秒钟。失败返回0。 
     * @param date 日期字符串 
     * @return 秒钟 
     */  
    public static int getSecond(String date) {  
        return getSecond(stringToDate(date));  
    }  
  
    /** 
     * 获取日期的秒钟。失败返回0。 
     * @param date 日期 
     * @return 秒钟 
     */  
    public static int getSecond(Date date) {  
        return getInteger(date, Calendar.SECOND);  
    }  
  
    /** 
     * 获取日期 。默认yyyy-MM-dd格式。失败返回null。 
     * @param date 日期字符串 
     * @return 日期 
     */  
    public static String getDate(String date) {  
        return stringToString(date, DateStyle.YYYY_MM_DD);  
    }  
  
    /** 
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。 
     * @param date 日期 
     * @return 日期 
     */  
    public static String getDate(Date date) {  
        return dateToString(date, DateStyle.YYYY_MM_DD);  
    }  
  
    /** 
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。 
     * @param date 日期字符串 
     * @return 时间 
     */  
    public static String getTime(String date) {  
        return stringToString(date, DateStyle.HH_MM_SS);  
    }  
  
    /** 
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。 
     * @param date 日期 
     * @return 时间 
     */  
    public static String getTime(Date date) {  
        return dateToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS);  
    }  
  
    /** 
     * 获取日期的星期。失败返回null。 
     * @param date 日期字符串 
     * @return 星期 
     */  
    public static Week getWeek(String date) {  
        Week week = null;  
        DateStyle dateStyle = getDateStyle(date);  
        if (dateStyle != null) {  
            Date myDate = stringToDate(date, dateStyle);  
            week = getWeek(myDate);  
        }  
        return week;  
    }  
  
    /** 
     * 获取日期的星期。失败返回null。 
     * @param date 日期 
     * @return 星期 
     */  
    public static Week getWeek(Date date) {  
        Week week = null;  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;  
        switch (weekNumber) {  
        case 0:  
            week = Week.SUNDAY;  
            break;  
        case 1:  
            week = Week.MONDAY;  
            break;  
        case 2:  
            week = Week.TUESDAY;  
            break;  
        case 3:  
            week = Week.WEDNESDAY;  
            break;  
        case 4:  
            week = Week.THURSDAY;  
            break;  
        case 5:  
            week = Week.FRIDAY;  
            break;  
        case 6:  
            week = Week.SATURDAY;  
            break;
        default:
        }
        return week;
    }

    /**
     * 获取两个日期相差的天数
     * @param date 日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差天数 
     */  
    public static int getIntervalDays(String date, String otherDate) {  
        return getIntervalDays(stringToDate(date), stringToDate(otherDate));  
    }  
      
    /** 
     * @param date 日期 
     * @param otherDate 另一个日期 
     * @return 相差天数 
     */  
    public static int getIntervalDays(Date date, Date otherDate) {  
        date = DateUtil.stringToDate(DateUtil.getDate(date));
        long time = Math.abs(date.getTime() - otherDate.getTime());  
        return (int)time/(24 * 60 * 60 * 1000);  
    }  
}  